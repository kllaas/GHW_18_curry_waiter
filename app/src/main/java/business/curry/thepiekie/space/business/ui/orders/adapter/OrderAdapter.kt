package business.curry.thepiekie.space.business.ui.orders.adapter

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import business.curry.thepiekie.space.business.data.model.OrderPlace
import business.curry.thepiekie.space.business.databinding.OrderItemBinding

class OrderAdapter(private val items: List<OrderPlace>, private val itemClickListener: (OrderPlace) -> Unit) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = OrderItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: OrderAdapter.ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(val binding: OrderItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: OrderPlace) {
            binding.orderPlace = item
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                itemClickListener(item)
            }
        }
    }
}