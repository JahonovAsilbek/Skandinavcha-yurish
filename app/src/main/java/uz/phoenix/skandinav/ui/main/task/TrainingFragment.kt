package uz.phoenix.skandinav.ui.main.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import phoenix.skandinav.R
import phoenix.skandinav.databinding.FragmentTrainingBinding
import uz.phoenix.skandinav.database.AppDatabase
import uz.phoenix.skandinav.database.entities.MainPart
import uz.phoenix.skandinav.database.entities.Rating
import uz.phoenix.skandinav.database.entities.Training
import uz.phoenix.skandinav.database.entities.User

private const val ARG_PARAM1 = "training"

class TrainingFragment : Fragment() {

    private var training: Training? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            training = it.getSerializable(ARG_PARAM1) as Training?
        }
    }

    lateinit var binding: FragmentTrainingBinding
    lateinit var mainPart: MainPart
    lateinit var navOptions: NavOptions.Builder
    lateinit var firebaseFireStore: FirebaseFirestore
    lateinit var users: ArrayList<Rating>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrainingBinding.inflate(layoutInflater)

        loadFirebase()
        setNavigation()
        loadMainPartData()
        loadDataToView()
        backClick()

        binding.entrance.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("training", training)
            findNavController().navigate(R.id.entranceFragment, bundle, navOptions.build())
        }

        binding.mainPart.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("main_part", mainPart)
            findNavController().navigate(R.id.mainPartFragment, bundle, navOptions.build())
        }

        binding.endPart.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("end_part", training)
            bundle.putSerializable("users", users)
            findNavController().navigate(R.id.preparationPartFragment, bundle, navOptions.build())
        }

        return binding.root
    }

    private fun loadFirebase() {
        firebaseFireStore = FirebaseFirestore.getInstance()
        users = ArrayList()

        firebaseFireStore.collection("users").get().addOnCompleteListener {
            val result = it.result
            result?.forEach { queryDocumentSnapshot ->
                val user = queryDocumentSnapshot.toObject(Rating::class.java)
                users.add(user)
            }
        }.addOnFailureListener {
            Toast.makeText(binding.root.context, "Failure", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadMainPartData() {
        if (training != null) {
            mainPart =
                AppDatabase.GET.getTrainingDatabase().getTrainingDao().getMainPart(training?.id!!)
        }
    }

    private fun backClick() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setNavigation() {
        navOptions = NavOptions.Builder()
        navOptions.setEnterAnim(R.anim.enter_from_right)
        navOptions.setPopEnterAnim(R.anim.enter_from_left)
        navOptions.setExitAnim(R.anim.exit_to_left)
        navOptions.setPopExitAnim(R.anim.exit_to_right)
    }

    private fun loadDataToView() {
        if (training != null) {
            binding.title.text = training!!.name
        }
    }
}