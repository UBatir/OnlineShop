package uz.example.onlineshop.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.gson.GsonBuilder
import org.koin.android.ext.android.inject
import uz.example.onlineshop.R
import uz.example.onlineshop.core.extentions.onClick
import uz.example.onlineshop.data.remote.Product
import uz.example.onlineshop.databinding.FragmentBasketBinding
import uz.example.onlineshop.databinding.FragmentFavoriteBinding
import uz.example.onlineshop.ui.basket.BasketAdapter
import uz.example.onlineshop.ui.basket.BasketFragmentDirections

class FavoriteFragment:Fragment(R.layout.fragment_favorite) {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var navController: NavController
    private val adapter: FavoriteAdapter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentFavoriteBinding.bind(view)
        navController= Navigation.findNavController(view)
        binding.apply {
            btnBack.onClick {
                navController.popBackStack()
            }
            recyclerView.adapter=adapter
        }
        adapter.models= listOf(
            Product("Куртка", "400000сум",false),
            Product("Джинсы","123400сум",true),
            Product("Джинсы","123400сум",true),
            Product("Куртка", "400000сум",false),
            Product("Куртка", "400000сум",false),
            Product("Джинсы","123400сум",false),
            Product("Джинсы","123400сум",false),
            Product("Куртка", "400000сум",false),
            Product("Куртка", "400000сум",false),
            Product("Джинсы","123400сум",false),
            Product("Джинсы","123400сум",true),
            Product("Куртка", "400000сум",false)
        )
        adapter.onClickItem {
            val gsonPretty = GsonBuilder().setPrettyPrinting().create()
            val gsonString = gsonPretty.toJson(it)
            findNavController().navigate(FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(gsonString))
        }
    }
}