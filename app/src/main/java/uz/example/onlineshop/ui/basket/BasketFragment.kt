package uz.example.onlineshop.ui.basket

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import org.koin.android.ext.android.inject
import uz.example.onlineshop.R
import uz.example.onlineshop.core.extentions.onClick
import uz.example.onlineshop.data.remote.Product
import uz.example.onlineshop.databinding.FragmentBasketBinding

class BasketFragment: Fragment(R.layout.fragment_basket) {

    private lateinit var binding:FragmentBasketBinding
    private lateinit var navController: NavController
    private val adapter: BasketAdapter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentBasketBinding.bind(view)
        navController=Navigation.findNavController(view)
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
    }
}