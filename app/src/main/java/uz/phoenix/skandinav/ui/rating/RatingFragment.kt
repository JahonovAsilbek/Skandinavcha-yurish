package uz.phoenix.skandinav.ui.rating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import phoenix.skandinav.databinding.FragmentRatingBinding
import uz.phoenix.skandinav.database.entities.Rating
import uz.phoenix.skandinav.ui.rating.adapters.RatingAdapter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RatingFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentRatingBinding
    lateinit var firebaseFireStore: FirebaseFirestore
    lateinit var users: ArrayList<Rating>
    lateinit var adapter: RatingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRatingBinding.inflate(layoutInflater)

        loadFireBase()
        popBackStack()

        return binding.root
    }

    private fun loadFireBase() {
        firebaseFireStore = FirebaseFirestore.getInstance()
        users = ArrayList()
        adapter = RatingAdapter()

        firebaseFireStore.collection("users").get().addOnCompleteListener {
            val result = it.result
            result?.forEach { queryDocumentSnapshot ->
                val user = queryDocumentSnapshot.toObject(Rating::class.java)
                users.add(user)
                val data = users.sortedBy { rating ->
                    rating.point
                }
                adapter.setAdapter(data.reversed())
                binding.rv.adapter = adapter
            }
        }.addOnFailureListener {
            Toast.makeText(binding.root.context, "Failure", Toast.LENGTH_SHORT).show()
        }
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
}