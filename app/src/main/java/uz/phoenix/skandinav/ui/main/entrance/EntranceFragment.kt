package uz.phoenix.skandinav.ui.main.entrance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import phoenix.skandinav.R
import phoenix.skandinav.databinding.FragmentEntranceBinding
import uz.phoenix.skandinav.database.entities.Training

private const val ARG_PARAM1 = "training"

class EntranceFragment : Fragment() {

    private var training: Training? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            training = it.getSerializable(ARG_PARAM1) as Training?
        }
    }

    lateinit var binding: FragmentEntranceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEntranceBinding.inflate(layoutInflater)

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.theoreticalInfo.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("training", training)
            findNavController().navigate(R.id.theoreticalInfoFragment, bundle)
        }

        binding.video.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("training", training)
            findNavController().navigate(R.id.videoFragment, bundle)
        }

        binding.tasks.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putSerializable("training", training)
//            findNavController().navigate(R.id.theoreticalInfoFragment, bundle)
        }

        return binding.root
    }

}