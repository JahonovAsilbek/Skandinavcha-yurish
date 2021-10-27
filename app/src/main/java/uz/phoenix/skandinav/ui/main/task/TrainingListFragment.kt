package uz.phoenix.skandinav.ui.main.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import phoenix.skandinav.R
import phoenix.skandinav.databinding.FragmentTrainingListBinding
import uz.phoenix.skandinav.database.entities.MainPart
import uz.phoenix.skandinav.database.entities.Month
import uz.phoenix.skandinav.database.entities.Training
import uz.phoenix.skandinav.ui.main.task.adapters.TrainingAdapter

private const val ARG_PARAM1 = "month"

class TrainingListFragment : Fragment() {

    private var month: Month? = null
    lateinit var navOptions: NavOptions.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            month = it.getSerializable(ARG_PARAM1) as Month?
        }
    }

    lateinit var binding: FragmentTrainingListBinding
    lateinit var adapter: TrainingAdapter
    lateinit var trainingList: ArrayList<Training>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrainingListBinding.inflate(layoutInflater)

        setNavigation()
        loadEntranceData()
        loadAdapter()
        itemClick()
        backClick()

        return binding.root
    }

    private fun backClick() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun itemClick() {
        adapter.onTrainingClick = object : TrainingAdapter.OnTrainingClick {
            override fun onClick(training: Training) {
                val bundle = Bundle()
                bundle.putSerializable("training", training)
                findNavController().navigate(R.id.trainingFragment, bundle,navOptions.build())
            }
        }
    }

    private fun loadAdapter() {
        adapter = TrainingAdapter()
        adapter.setAdapter(trainingList)
        binding.rv.adapter = adapter
    }

    private fun setNavigation() {
        navOptions = NavOptions.Builder()
        navOptions.setEnterAnim(R.anim.enter_from_right)
        navOptions.setPopEnterAnim(R.anim.enter_from_left)
        navOptions.setExitAnim(R.anim.exit_to_left)
        navOptions.setPopExitAnim(R.anim.exit_to_right)
    }

    private fun loadEntranceData() {
        trainingList = ArrayList()
//        trainingList = TrainingDatabase.Get.getTrainingDatabase().trainingDao().getTraining(month?.id!!)
        for (i in 0 until 12) {

            trainingList.add(
                Training(
                    i,
                    i,
                    "${i + 1}-mashg'ulot",
                    null,
                    "HCfPhZQz2CE",
                    null,
                    null,
                    "HCfPhZQz2CE",
                    null,
                    "HCfPhZQz2CE",
                    null,
                    "HCfPhZQz2CE",
                    true,
                    null,
                    true
                )
            )
        }
    }
}