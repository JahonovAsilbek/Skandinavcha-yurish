package uz.phoenix.skandinav.ui.settings

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.florent37.runtimepermission.kotlin.askPermission
import phoenix.skandinav.R
import phoenix.skandinav.databinding.FragmentSettingsBinding
import uz.phoenix.skandinav.database.UserDatabase
import uz.phoenix.skandinav.database.entities.User
import java.io.File
import java.io.FileOutputStream

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private var imagePath = ""
    lateinit var profileChangeListener: ProfileChangeListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        loadDataToView()
        cameraClick()
        editClick()

        popBackStack()
        return binding.root
    }

    private fun editClick() {
        binding.editBtn.setOnClickListener {
            UserDatabase.Get.getUserDatabase().getDao().updateUser(
                User(
                    binding.name.text.toString().trim(),
                    binding.surname.text.toString().trim(),
                    binding.birthday.text.toString().trim(),
                    binding.phoneNumber.text.toString().trim(),
                    binding.aboutMe.text.toString().trim(),
                    imagePath
                )
            )
            profileChangeListener.dataChangeListener(User(
                binding.name.text.toString().trim(),
                binding.surname.text.toString().trim(),
                binding.birthday.text.toString().trim(),
                binding.phoneNumber.text.toString().trim(),
                binding.aboutMe.text.toString().trim(),
                imagePath
            ))
            findNavController().popBackStack()
        }
    }

    private fun loadDataToView() {
        val user = UserDatabase.Get.getUserDatabase().getDao().getUser()
        if (user.imagePath != null) {
            binding.profileImage.setImageURI(Uri.parse(user.imagePath))
            imagePath = user.imagePath!!
        }
        binding.name.setText(user.name)
        binding.surname.setText(user.surname)
        binding.birthday.setText(user.birthday)
        binding.phoneNumber.setText(user.phoneNumber)
        binding.aboutMe.setText(user.info)
    }

    private fun cameraClick() {
        binding.camera.setOnClickListener {
            askPermission(
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) {
                //all permissions already granted or just granted
                getImageContent.launch("image/*")
            }.onDeclined { e ->
                if (e.hasDenied()) {


                    AlertDialog.Builder(binding.root.context, R.style.RoundedCornersDialog)
                        .setMessage("Rasm uchun qurilma xotirasiga ruxsat berish zarur")
                        .setPositiveButton("OK") { dialog, which ->
                            e.askAgain();
                        } //ask again
                        .setNegativeButton("Bekor qilish") { dialog, which ->
                            dialog.dismiss();
                        }
                        .show();
                }
                if (e.hasForeverDenied()) {
                    // you need to open setting manually if you really need it
                    e.goToSettings();
                }
            }
        }

    }

    //upload image from GALLERY
    private val getImageContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri ?: return@registerForActivityResult
            binding.profileImage.setImageURI(uri)
            val ins = activity?.contentResolver?.openInputStream(uri)
            val file = File(activity?.filesDir, "imageProfile.jpg")
            val fileOutputStream = FileOutputStream(file)
            ins?.copyTo(fileOutputStream)
            ins?.close()
            fileOutputStream.close()
            imagePath = file.absolutePath
        }

    private fun popBackStack() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
//                    findNavController().popBackStack(R.id.nav_home, false)
                    findNavController().popBackStack()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface ProfileChangeListener {
        fun dataChangeListener(user: User)
    }

    override fun onAttach(context: Context) {
        profileChangeListener = context as ProfileChangeListener
        super.onAttach(context)
    }
}