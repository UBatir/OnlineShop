package uz.example.onlineshop.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import org.koin.android.ext.android.inject
import uz.example.onlineshop.R
import uz.example.onlineshop.core.extentions.onClick
import uz.example.onlineshop.data.remote.Product
import uz.example.onlineshop.databinding.FragmentSearchBinding

class SearchFragment:Fragment(R.layout.fragment_search) {

    private lateinit var binding: FragmentSearchBinding
    private val adapter: SearchAdapter by inject()
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentSearchBinding.bind(view)
        navController=Navigation.findNavController(view)
        binding.apply {
            recyclerView.adapter = adapter
            btnBack.onClick {
                navController.popBackStack()
            }
        }
        adapter.models = listOf(
            Product("Куртка", "400000сум", false),
            Product("Джинсы", "123400сум", true),
            Product("Джинсы", "123400сум", true),
            Product("Куртка", "400000сум", false),
            Product("Куртка", "400000сум", false),
            Product("Джинсы", "123400сум", false),
            Product("Джинсы", "123400сум", false),
            Product("Куртка", "400000сум", false),
            Product("Куртка", "400000сум", false),
            Product("Джинсы", "123400сум", false),
            Product("Джинсы", "123400сум", true),
            Product("Куртка", "400000сум", false)
        )
    }
}