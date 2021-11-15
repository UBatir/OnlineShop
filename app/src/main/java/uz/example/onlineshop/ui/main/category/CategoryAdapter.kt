package uz.example.onlineshop.ui.main.category

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.example.onlineshop.R
import uz.example.onlineshop.core.extentions.inflate
import uz.example.onlineshop.data.remote.Product
import uz.example.onlineshop.databinding.ItemProductBinding

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    var models:List<Product> = listOf()
        set(value) {
            field=value
            notifyDataSetChanged()
        }

    inner class CategoryViewHolder(private val binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root){
        fun populateModel(model: Product){
            binding.apply {
                tvTitle.text=model.name
                tvPrice.text=model.price
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView=parent.inflate(R.layout.item_product)
        val binding= ItemProductBinding.bind(itemView)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount()=models.size

}