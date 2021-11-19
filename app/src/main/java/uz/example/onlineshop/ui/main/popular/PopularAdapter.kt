package uz.example.onlineshop.ui.main.popular

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.example.onlineshop.R
import uz.example.onlineshop.core.extentions.inflate
import uz.example.onlineshop.core.extentions.onClick
import uz.example.onlineshop.data.remote.Product
import uz.example.onlineshop.databinding.ItemProductBinding

class PopularAdapter:RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    var models:List<Product> = listOf()
    set(value) {
        field=value
        notifyDataSetChanged()
    }

    private var clickItemFavorite:()->Unit={}
    fun onClickItemFavorite(clickItem:()->Unit) {
        this.clickItemFavorite = clickItem
    }

    private var clickItem:(product:Product)->Unit={}
    fun onClickItem(clickItem:(product:Product)->Unit){
        this.clickItem=clickItem
    }

    inner class PopularViewHolder(private val binding: ItemProductBinding):RecyclerView.ViewHolder(binding.root){
        fun populateModel(model:Product){
            binding.apply {
                tvTitle.text=model.name
                tvPrice.text=model.price
                if (model.isFav) iconFav.setBackgroundResource(R.drawable.ic_fav_icon)
                else iconFav.setBackgroundResource(R.drawable.ic_favorite)
                binding.iconFav.onClick {
                    clickItemFavorite.invoke()
                    if(model.isFav) {
                        model.isFav=false
                        iconFav.setBackgroundResource(R.drawable.ic_favorite)
                    }
                    else{
                        model.isFav=true
                        iconFav.setBackgroundResource(R.drawable.ic_fav_icon)
                    }
                }
            }
            binding.root.onClick {
                clickItem.invoke(model)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val itemView=parent.inflate(R.layout.item_product)
        val binding=ItemProductBinding.bind(itemView)
        return PopularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount()=models.size


}