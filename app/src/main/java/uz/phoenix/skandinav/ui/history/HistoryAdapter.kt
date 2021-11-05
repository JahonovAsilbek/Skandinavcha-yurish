package uz.phoenix.skandinav.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import phoenix.skandinav.databinding.ItemHistoryBinding
import uz.phoenix.skandinav.database.entities.History

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryVH>() {

    lateinit var data: List<History>
    lateinit var onHistoryItemClick: OnHistoryItemClick

    fun setAdapter(data: List<History>) {
        this.data = data
    }

    inner class HistoryVH(var itemHistoryBinding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(itemHistoryBinding.root) {

        fun onBind(history: History) {
            itemHistoryBinding.training.text = history.trainingName

            itemView.setOnClickListener {
                onHistoryItemClick.onClick(history)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH {
        return HistoryVH(ItemHistoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: HistoryVH, position: Int) {
        holder.onBind(data[position])
    }

    override fun getItemCount(): Int = data.size

    interface OnHistoryItemClick {
        fun onClick(history: History)
    }
}