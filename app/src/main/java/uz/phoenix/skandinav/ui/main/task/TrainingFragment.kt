package uz.phoenix.skandinav.ui.main.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import phoenix.skandinav.R
import phoenix.skandinav.databinding.FragmentTrainingBinding
import uz.phoenix.skandinav.database.entities.Training

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrainingBinding.inflate(layoutInflater)

        loadDataToView()
        backClick()

        binding.entrance.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("training", training)
            findNavController().navigate(R.id.entranceFragment, bundle)
        }

        binding.mainPart.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("training", training)
//            findNavController().navigate(R.id.entranceFragment, bundle)
            Toast.makeText(binding.root.context, "Main part", Toast.LENGTH_SHORT).show()
        }

        binding.endPart.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("training", training)
//            findNavController().navigate(R.id.entranceFragment, bundle)
            Toast.makeText(binding.root.context, "End part", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    private fun backClick() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun loadDataToView() {
        if (training != null) {
            binding.title.text = training!!.name
        }
    }
}