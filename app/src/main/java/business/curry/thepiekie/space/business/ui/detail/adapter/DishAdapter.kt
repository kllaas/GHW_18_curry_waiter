package business.curry.thepiekie.space.business.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import business.curry.thepiekie.space.business.data.model.Dish
import business.curry.thepiekie.space.business.databinding.DishItemBinding
import androidx.databinding.BindingAdapter
import business.curry.thepiekie.space.business.R
import com.bumptech.glide.Glide
import androidx.databinding.ObservableField




class DishAdapter(private val items: List<Dish>) : RecyclerView.Adapter<DishAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DishItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DishAdapter.ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(val binding: DishItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Dish) {
            binding.dish = item
            binding.executePendingBindings()
        }

        @BindingAdapter("imageUrl")
        fun setImageUrl(imageView: ImageView, url: String) {
            val context = imageView.context
            Glide.with(context).load(url).into(imageView)
        }
    }

}