package uz.example.onlineshop.ui.main.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import uz.example.onlineshop.R
import uz.example.onlineshop.data.remote.Product
import uz.example.onlineshop.databinding.FragmentCategoryBinding
import uz.example.onlineshop.ui.main.popular.PopularAdapter

class CategoryFragment:Fragment(R.layout.fragment_category) {

    private lateinit var binding: FragmentCategoryBinding
    private val adapter: PopularAdapter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentCategoryBinding.bind(view)
        binding.apply {
            recyclerView.adapter=adapter
        }
        adapter.models= listOf(
            Product("Куртка", "400000сум",false), Product("Джинсы","123400сум",true),
            Product("Джинсы","123400сум",false),
            Product("Куртка", "400000сум",false)
        )
    }
}