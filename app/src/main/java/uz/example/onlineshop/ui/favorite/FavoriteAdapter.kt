package uz.example.onlineshop.ui.favorite

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.example.onlineshop.R
import uz.example.onlineshop.core.extentions.inflate
import uz.example.onlineshop.core.extentions.onClick
import uz.example.onlineshop.data.remote.Product
import uz.example.onlineshop.databinding.ItemFavoriteBinding

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.FavViewHolder>() {

    var models:List<Product> = listOf()
        set(value) {
            field=value
            notifyDataSetChanged()
        }


    private var clickItem:(product:Product)->Unit={}
    fun onClickItem(clickItem:(product:Product)->Unit){
        this.clickItem=clickItem
    }
    inner class FavViewHolder(private val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root){
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val itemVew = parent.inflate(R.layout.item_favorite)
        val binding = ItemFavoriteBinding.bind(itemVew)
        return FavViewHolder(binding)
    }
    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.populateModel(models[position])
    }
    override fun getItemCount(): Int = models.size
}