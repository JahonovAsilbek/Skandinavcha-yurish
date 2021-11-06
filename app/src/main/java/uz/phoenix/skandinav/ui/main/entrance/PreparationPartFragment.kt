package uz.phoenix.skandinav.ui.main.entrance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.squareup.picasso.Picasso
import phoenix.skandinav.R
import phoenix.skandinav.databinding.FragmentPreparationPartBinding
import phoenix.skandinav.databinding.InfoDialog3Binding
import uz.phoenix.skandinav.database.AppDatabase
import uz.phoenix.skandinav.database.UserDatabase
import uz.phoenix.skandinav.database.entities.*

private const val ARG_PARAM1 = "training"
private const val ARG_PARAM2 = "main_part"
private const val ARG_PARAM3 = "nord_walking"
private const val ARG_PARAM4 = "end_part"
private const val ARG_PARAM5 = "tournament"
private const val ARG_PARAM6 = "users"

class PreparationPartFragment : Fragment() {

    private var training: Training? = null
    private var endPart: Training? = null
    private var mainPart: MainPart? = null
    private var nordWalking: MainPart? = null
    private var tournament: Tournament? = null
    private var users: ArrayList<Rating>? = null
    lateinit var user: User
    lateinit var view: InfoDialog3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            training = it.getSerializable(ARG_PARAM1) as Training?
            mainPart = it.getSerializable(ARG_PARAM2) as MainPart?
            nordWalking = it.getSerializable(ARG_PARAM3) as MainPart?
            endPart = it.getSerializable(ARG_PARAM4) as Training?
            tournament = it.getSerializable(ARG_PARAM5) as Tournament?
            users = it.getSerializable(ARG_PARAM6) as ArrayList<Rating>?
        }
    }

    lateinit var binding: FragmentPreparationPartBinding
    lateinit var firebaseFireStore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPreparationPartBinding.inflate(layoutInflater)

        loadTraining()
        loadMainPart()
        nordWalking1()
        endPart1()
        tournament1()
        backClick(user.point)

        return binding.root
    }

    private fun endPart1() {
        if (endPart != null) {
            binding.title.text = "Yakuniy\nqism"
            binding.preparationPart.text = endPart!!.endPart
            binding.image.visibility = View.GONE
            AppDatabase.GET.getTrainingDatabase().getTrainingDao().finishTraining(endPart?.id!! + 1)
            user = UserDatabase.Get.getUserDatabase().getDao().getUser()
            val finishedTrainings =
                UserDatabase.Get.getUserDatabase().getDao().getFinishedTrainingByName(
                    endPart?.monthId!!,
                    endPart?.name!!
                )

            if (finishedTrainings.isEmpty()) {
                UserDatabase.Get.getUserDatabase().getDao().updateUser(
                    User(
                        user.uId,
                        user.name,
                        user.surname,
                        user.birthday,
                        user.phoneNumber,
                        user.info,
                        user.imagePath,
                        user.point + 150
                    )
                )
                user = UserDatabase.Get.getUserDatabase().getDao().getUser()
                // set database when training finishes
                // its uses for daily fragment
                UserDatabase.Get.getUserDatabase().getDao().insertFinishedTraining(
                    Finished(
                        endPart?.monthId,
                        endPart?.name,
                        false
                    )
                )
                // update firestore database
                loadFirebase(user)
            }
            popBackStack(user.point)
        }
    }

    private fun checkUser(name: String): Boolean {
        var check = true
        for (user in users!!) {
            if (user.name == name) {
                check = false
            }
        }
        return check
    }

    private fun addUserToFirebase(user: User) {
        firebaseFireStore.collection("users").document(user.uId)
            .set(Rating(user.name, user.surname, user.point + 150))
            .addOnSuccessListener {
                Toast.makeText(binding.root.context, "Success", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(binding.root.context, "Failure", Toast.LENGTH_SHORT).show()
            }
    }

    private fun loadFirebase(user: User) {
        firebaseFireStore = FirebaseFirestore.getInstance()

        if (checkUser(user.name + user.surname)) {
            addUserToFirebase(user)
        } else updateUserFirebase(user)

    }

    private fun updateUserFirebase(user: User) {
        val map = HashMap<Any, String>()
        map["point"] = user.point.toString()
        firebaseFireStore.collection("users").document(user.uId)
            .set(map, SetOptions.merge()).addOnCompleteListener {
                Toast.makeText(binding.root.context, "Success", Toast.LENGTH_SHORT).show()
            }
    }

    private fun backClick(point: Int) {
        binding.back.setOnClickListener {
            if (endPart != null) {
                val dialog =
                    AlertDialog.Builder(binding.root.context, R.style.RoundedCornersDialog)
                val alertDialog = dialog.create()
                val view = InfoDialog3Binding.inflate(layoutInflater, null, false)
                view.root.setBackgroundColor(resources.getColor(R.color.white))
                view.allPoints.text = "Jami ballar: $point"
                view.ok.setOnClickListener {
                    alertDialog.cancel()
                }
                alertDialog.setView(view.root)
                alertDialog.show()
                findNavController().popBackStack(R.id.trainingListFragment, false)
            } else
                findNavController().popBackStack()
        }
    }

    private fun tournament1() {
        if (tournament != null) {
            binding.title.text = tournament!!.name
            binding.preparationPart.text = tournament!!.text
            binding.image.visibility = View.GONE
        }
    }

    private fun nordWalking1() {
        if (nordWalking != null) {
            binding.title.text = "Skandinavcha\nyurish"
            binding.preparationPart.text = "Bugun ${mainPart!!.nordWalking} metr yuring"
            binding.image.visibility = View.GONE
        }
    }

    private fun loadMainPart() {
        if (mainPart != null) {
            binding.title.text = "Harakatli\no'yinlar"
            binding.preparationPart.text = mainPart!!.actionGame
            binding.image.visibility = View.VISIBLE
            Picasso.get().load(mainPart!!.actionGameImage).error(R.drawable.walking)
                .into(binding.image)
        }
    }

    private fun loadTraining() {
        if (training != null) {
            binding.title.text = "Tayyorlov qismi\n(3-5 daqiqa)"
            binding.preparationPart.text = training!!.preparationPart
            binding.image.visibility = View.GONE
        }
    }

    private fun popBackStack(point: Int) {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    val dialog =
                        AlertDialog.Builder(binding.root.context, R.style.RoundedCornersDialog)
                    val alertDialog = dialog.create()
                    view = InfoDialog3Binding.inflate(layoutInflater, null, false)
                    view.root.setBackgroundColor(resources.getColor(R.color.white))
                    view.allPoints.text = "Jami ballar: ${point}"
                    view.ok.setOnClickListener {
                        alertDialog.cancel()
                    }
                    alertDialog.setView(view.root)
                    alertDialog.show()
                    findNavController().popBackStack(R.id.trainingListFragment, false)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

}