package uz.phoenix.skandinav.ui.main.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import phoenix.skandinav.R
import phoenix.skandinav.databinding.FragmentTrainingListBinding
import uz.phoenix.skandinav.database.entities.Month
import uz.phoenix.skandinav.database.entities.Training
import uz.phoenix.skandinav.ui.main.task.adapters.TrainingAdapter

private const val ARG_PARAM1 = "month"

class TrainingListFragment : Fragment() {

    private var month: Month? = null

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

        loadData()
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
                findNavController().navigate(R.id.trainingFragment, bundle)
            }
        }
    }

    private fun loadAdapter() {
        adapter = TrainingAdapter()
        adapter.setAdapter(trainingList)
        binding.rv.adapter = adapter
    }

    private fun loadData() {
        trainingList = ArrayList()
//        trainingList = TrainingDatabase.Get.getTrainingDatabase().trainingDao().getTraining(month?.id!!)
        for (i in 0 until 12) {

            trainingList.add(
                Training(
                    i,
                    i,
                    "${i + 1}-mashg'ulot",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    true,
                    true
                )
            )
        }
    }
}