package uz.phoenix.skandinav.ui.daily.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import phoenix.skandinav.databinding.ItemDailyBinding

class DailyAdapter : RecyclerView.Adapter<DailyAdapter.DailyVH>() {

    var onDailyItemClick: OnDailyItemClick? = null
    private lateinit var data: List<String>

    fun setAdapter(data: List<String>) {
        this.data = data
    }

    inner class DailyVH(var itemDailyBinding: ItemDailyBinding) :
        RecyclerView.ViewHolder(itemDailyBinding.root) {

        fun onBind(string: String, position: Int) {
            itemDailyBinding.text.text = string

            itemView.setOnClickListener {
                onDailyItemClick?.onClick(string, position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyVH {
        return DailyVH(ItemDailyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: DailyVH, position: Int) {
        holder.onBind(data[position], position)
    }

    override fun getItemCount(): Int = data.size

    interface OnDailyItemClick {
        fun onClick(string: String, position: Int)
    }
}