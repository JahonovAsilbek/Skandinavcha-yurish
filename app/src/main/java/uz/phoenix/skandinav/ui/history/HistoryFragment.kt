package uz.phoenix.skandinav.ui.history

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import phoenix.skandinav.R
import phoenix.skandinav.databinding.FragmentHistoryBinding
import phoenix.skandinav.databinding.HistoryDialogBinding
import uz.phoenix.skandinav.database.UserDatabase
import uz.phoenix.skandinav.database.entities.History
import uz.phoenix.skandinav.database.entities.Month

private const val ARG_PARAM1 = "month"
private const val ARG_PARAM2 = "param2"

class HistoryFragment : Fragment() {

    private var month: Month? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            month = it.getSerializable(ARG_PARAM1) as Month
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentHistoryBinding
    lateinit var data: List<History>
    lateinit var adapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(layoutInflater)

        backClick()
        loadData()
        itemClick()

        return binding.root
    }

    private fun itemClick() {
        adapter.onHistoryItemClick = object : HistoryAdapter.OnHistoryItemClick {
            @SuppressLint("SetTextI18n")
            override fun onClick(history: History) {
                val dialog = AlertDialog.Builder(binding.root.context, R.style.RoundedCornersDialog)
                val alertDialog = dialog.create()
                val view = HistoryDialogBinding.inflate(layoutInflater, null, false)
                view.root.setBackgroundColor(resources.getColor(R.color.white))
                view.ok.setOnClickListener {
                    alertDialog.cancel()
                }
                view.title.text = history.trainingName
                view.health.text = history.health
                view.mood.text = history.mood
                view.appetite.text = history.appetite
                view.desire.text = history.desire
                view.sleep.text = history.sleep+" soat"
                view.trainingStartTime.text = history.trainingStartTime
                view.trainingEndTime.text = history.trainingEndTime
                view.nordWalkingLength.text = history.nordWalkingLength+" m"
                view.nordWalkingTime.text = history.nordWalkingTime+" min"
                view.trainingEntrancePeriod.text = history.trainingEntrancePeriod+" min"
                view.trainingMainPart.text = history.trainingMainPartPeriod+" min"
                view.trainingEndPart.text = history.trainingEndPartPeriod+" min"
                view.fatigue.text = history.fatigue
                view.disorder.text = history.disorder
                alertDialog.setView(view.root)
                alertDialog.show()
            }
        }
    }

    private fun loadData() {
        if (month != null) {
            data = UserDatabase.Get.getUserDatabase().getDao().getHistoryByMonthId(month?.id!!)
            adapter = HistoryAdapter()
            adapter.setAdapter(data)
            binding.rv.adapter = adapter
        }
    }

    private fun backClick() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }


}