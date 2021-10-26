package uz.phoenix.skandinav.ui.main.entrance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import phoenix.skandinav.R
import phoenix.skandinav.databinding.FragmentUrmTasksBinding
import uz.phoenix.skandinav.database.entities.Training

private const val ARG_PARAM1 = "training"

class UrmTasksFragment : Fragment() {

    private var training: Training? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            training = it.getSerializable(ARG_PARAM1) as Training?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentUrmTasksBinding.inflate(layoutInflater)

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.preparationPart.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("training", training)
            findNavController().navigate(R.id.preparationPartFragment, bundle)
        }

        binding.urmTasks.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("training", training)
            findNavController().navigate(R.id.tasksListFragment, bundle)
        }

        return binding.root
    }
}