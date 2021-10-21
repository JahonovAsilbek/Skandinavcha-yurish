package uz.phoenix.skandinav.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import phoenix.skandinav.R
import phoenix.skandinav.databinding.FragmentShowUserDataBinding
import uz.phoenix.skandinav.database.UserDatabase

class ShowUserDataFragment : Fragment() {

    lateinit var binding: FragmentShowUserDataBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowUserDataBinding.inflate(layoutInflater)

        loadDataToView()
        backClick()
        popBackStack()
        editClick()

        return binding.root
    }

    private fun editClick() {
        binding.editBtn.setOnClickListener {
            val userData = UserDatabase.Get.getUserDatabase().getDao().getUserData()
            if (userData != null) {
                val bundle = Bundle()
                bundle.putSerializable("userData", userData)
                findNavController().navigate(R.id.inputDataFragment2, bundle)
            } else findNavController().navigate(R.id.inputDataFragment2)
        }
    }

    private fun backClick() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun loadDataToView() {
        val userData = UserDatabase.Get.getUserDatabase().getDao().getUserData()
        binding.apply {
            if (userData != null) {
                height.text = userData.height
                age.text = userData.age
                heightStick.text = userData.stickLength
            }
        }
    }

    private fun popBackStack() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    override fun onResume() {
        super.onResume()
        loadDataToView()
    }
}