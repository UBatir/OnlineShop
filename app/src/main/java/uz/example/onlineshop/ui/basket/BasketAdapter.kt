package uz.example.onlineshop.ui.basket

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.example.onlineshop.R
import uz.example.onlineshop.core.extentions.inflate
import uz.example.onlineshop.core.extentions.onClick
import uz.example.onlineshop.data.remote.Product
import uz.example.onlineshop.databinding.ItemBasketBinding

class BasketAdapter:RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    var models:List<Product> = listOf()
    set(value) {
        field=value
        notifyDataSetChanged()
    }

    private var clickItem:(product:Product)->Unit={}
    fun onClickItem(clickItem:(product:Product)->Unit){
        this.clickItem=clickItem
    }

    inner class BasketViewHolder(private val binding:ItemBasketBinding):RecyclerView.ViewHolder(binding.root){
        fun populateModel(model:Product){
            binding.apply {
                tvTitle.text=model.name
                tvPrice.text=model.price
            }

            binding.root.onClick {
                clickItem.invoke(model)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val itemView=parent.inflate(R.layout.item_basket)
        val binding=ItemBasketBinding.bind(itemView)
        return BasketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount()=models.size
}