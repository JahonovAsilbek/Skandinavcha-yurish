package uz.phoenix.skandinav.ui.rating.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import phoenix.skandinav.databinding.ItemRatingBinding
import uz.phoenix.skandinav.database.entities.Rating

class RatingAdapter : RecyclerView.Adapter<RatingAdapter.RatingVH>() {

    private lateinit var data: List<Rating>

    fun setAdapter(data: List<Rating>) {
        this.data = data
    }

    inner class RatingVH(var itemRatingBinding: ItemRatingBinding) :
        RecyclerView.ViewHolder(itemRatingBinding.root) {

        fun onBind(rating: Rating, position: Int) {

//            when (position) {
//                0 -> itemRatingBinding.layout.setBackgroundColor(Color.parseColor("#FFD700"))
//                1 -> itemRatingBinding.layout.setBackgroundColor(Color.parseColor("#C0C0C0"))
//                2 -> itemRatingBinding.layout.setBackgroundColor(Color.parseColor("#CD7F32"))
//            }

            itemRatingBinding.name.text = rating.name + "\n" + rating.surname
            itemRatingBinding.point.text = rating.point.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingVH {
        return RatingVH(
            ItemRatingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RatingVH, position: Int) {
        holder.onBind(data[position], position)
    }

    override fun getItemCount(): Int = data.size
}