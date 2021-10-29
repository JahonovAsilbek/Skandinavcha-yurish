package uz.phoenix.skandinav.ui.main.entrance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
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
    lateinit var navOptions: NavOptions.Builder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEntranceBinding.inflate(layoutInflater)

        setNavigation()

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.theoreticalInfo.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("training", training)
            findNavController().navigate(R.id.theoreticalInfoFragment, bundle, navOptions.build())
        }

        binding.video.setOnClickListener {
            binding.progress.visibility = View.VISIBLE
            val bundle = Bundle()
            bundle.putSerializable("training", training)
            findNavController().navigate(R.id.videoFragment, bundle, navOptions.build())
        }

        binding.tasks.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("training", training)
            findNavController().navigate(R.id.urmTasksFragment, bundle, navOptions.build())
        }

        return binding.root
    }

    private fun setNavigation() {
        navOptions = NavOptions.Builder()
        navOptions.setEnterAnim(R.anim.enter_from_right)
        navOptions.setPopEnterAnim(R.anim.enter_from_left)
        navOptions.setExitAnim(R.anim.exit_to_left)
        navOptions.setPopExitAnim(R.anim.exit_to_right)
    }

    override fun onResume() {
        super.onResume()
        binding.progress.visibility = View.INVISIBLE
    }

}