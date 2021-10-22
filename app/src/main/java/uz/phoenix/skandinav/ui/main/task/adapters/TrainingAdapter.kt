package uz.phoenix.skandinav.ui.main.task.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import phoenix.skandinav.R
import phoenix.skandinav.databinding.ItemMonthBinding
import uz.phoenix.skandinav.database.entities.Training

class TrainingAdapter : RecyclerView.Adapter<TrainingAdapter.MonthVH>() {

    private lateinit var trainingList: List<Training>
    lateinit var onTrainingClick: OnTrainingClick

    fun setAdapter(trainingList: List<Training>) {
        this.trainingList = trainingList
    }

    inner class MonthVH(var itemMonthBinding: ItemMonthBinding) :
        RecyclerView.ViewHolder(itemMonthBinding.root) {

        fun onBind(training: Training) {
            itemMonthBinding.title.text = training.name

            if (training.isFinished == false) {
                itemMonthBinding.lock.visibility = View.VISIBLE
                itemMonthBinding.title.setBackgroundResource(R.drawable.training_locked_back)
            }

            itemView.setOnClickListener {
                onTrainingClick.onClick(training)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthVH {
        return MonthVH(ItemMonthBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MonthVH, position: Int) {
        holder.onBind(trainingList[position])
    }

    override fun getItemCount(): Int = trainingList.size

    interface OnTrainingClick {
        fun onClick(training: Training)
    }
}