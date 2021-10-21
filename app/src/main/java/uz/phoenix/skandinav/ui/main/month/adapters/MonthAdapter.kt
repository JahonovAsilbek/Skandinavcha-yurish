package uz.phoenix.skandinav.ui.main.month.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import phoenix.skandinav.databinding.ItemMonthBinding
import uz.phoenix.skandinav.database.entities.Month

class MonthAdapter : RecyclerView.Adapter<MonthAdapter.MonthVH>() {

    private lateinit var monthList: List<Month>
    lateinit var onMonthClick: OnMonthClick

    fun setAdapter(monthList: List<Month>) {
        this.monthList = monthList
    }

    inner class MonthVH(var itemMonthBinding: ItemMonthBinding) :
        RecyclerView.ViewHolder(itemMonthBinding.root) {

        fun onBind(month: Month) {
            itemMonthBinding.title.text = month.name

            itemView.setOnClickListener {
                onMonthClick.onClick(month)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthVH {
        return MonthVH(ItemMonthBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MonthVH, position: Int) {
        holder.onBind(monthList[position])
    }

    override fun getItemCount(): Int = monthList.size

    interface OnMonthClick {
        fun onClick(month: Month)
    }
}