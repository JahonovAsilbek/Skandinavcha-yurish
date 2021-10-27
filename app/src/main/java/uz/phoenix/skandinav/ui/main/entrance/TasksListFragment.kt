package uz.phoenix.skandinav.ui.main.entrance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import phoenix.skandinav.R
import phoenix.skandinav.databinding.FragmentTasksListBinding
import uz.phoenix.skandinav.database.entities.MainPart
import uz.phoenix.skandinav.database.entities.Training

private const val ARG_PARAM1 = "training"
private const val ARG_PARAM2 = "main_part"

class TasksListFragment : Fragment() {

    private var training: Training? = null
    private var mainPart: MainPart? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            training = it.getSerializable(ARG_PARAM1) as Training?
            mainPart = it.getSerializable(ARG_PARAM2) as MainPart?
        }
    }

    lateinit var binding: FragmentTasksListBinding
    lateinit var navOptions: NavOptions.Builder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTasksListBinding.inflate(layoutInflater)

        setNavigation()

        if (training != null) {
            entrancePart()
        }

        if (mainPart != null) {
            mainpart()
        }

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    private fun mainpart() {
        val bundle = Bundle()
        bundle.putSerializable("main_part", mainPart)
        binding.task1.setOnClickListener {
            binding.progress1.visibility = View.VISIBLE
            bundle.putInt("task_position", 1)
            findNavController().navigate(R.id.taskFragment, bundle, navOptions.build())
        }

        binding.task2.setOnClickListener {
            binding.progress2.visibility = View.VISIBLE
            bundle.putInt("task_position", 2)
            findNavController().navigate(R.id.taskFragment, bundle, navOptions.build())
        }

        binding.task3.setOnClickListener {
            binding.progress3.visibility = View.VISIBLE
            bundle.putInt("task_position", 3)
            findNavController().navigate(R.id.taskFragment, bundle, navOptions.build())
        }
    }

    private fun entrancePart() {
        val bundle = Bundle()
        bundle.putSerializable("training", training)
        binding.task1.setOnClickListener {
            binding.progress1.visibility = View.VISIBLE
            bundle.putInt("task_position", 1)
            findNavController().navigate(R.id.taskFragment, bundle)
        }

        binding.task2.setOnClickListener {
            binding.progress2.visibility = View.VISIBLE
            bundle.putInt("task_position", 2)
            findNavController().navigate(R.id.taskFragment, bundle)
        }

        binding.task3.setOnClickListener {
            binding.progress3.visibility = View.VISIBLE
            bundle.putInt("task_position", 3)
            findNavController().navigate(R.id.taskFragment, bundle)
        }
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
        binding.progress1.visibility = View.INVISIBLE
        binding.progress2.visibility = View.INVISIBLE
        binding.progress3.visibility = View.INVISIBLE
    }

}