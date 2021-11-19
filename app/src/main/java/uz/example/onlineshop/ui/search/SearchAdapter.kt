package uz.example.onlineshop.ui.search

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.example.onlineshop.R
import uz.example.onlineshop.core.extentions.inflate
import uz.example.onlineshop.core.extentions.onClick
import uz.example.onlineshop.data.remote.Product
import uz.example.onlineshop.databinding.ItemProductBinding

class SearchAdapter:RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    var models:List<Product> = listOf()
    set(value) {
        field=value
        notifyDataSetChanged()
    }

    private var clickItemFavorite:()->Unit={}
    fun onClickItemFavorite(clickItem:()->Unit){
        this.clickItemFavorite=clickItem
    }

    inner class SearchViewHolder(private val binding:ItemProductBinding):RecyclerView.ViewHolder(binding.root){
        fun populateModel(model:Product){
            binding.apply {
                tvTitle.text=model.name
                tvPrice.text=model.price
                if (model.isFav) iconFav.setImageResource(R.drawable.ic_fav_icon)
                else iconFav.setImageResource(R.drawable.ic_favorite)
                binding.iconFav.onClick {
                    clickItemFavorite.invoke()
                    if(model.isFav) {
                        model.isFav=false
                        iconFav.setImageResource(R.drawable.ic_favorite)
                    }
                    else{
                        model.isFav=true
                        iconFav.setImageResource(R.drawable.ic_fav_icon)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView=parent.inflate(R.layout.item_product)
        val binding=ItemProductBinding.bind(itemView)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount()=models.size
}