package uz.example.onlineshop.ui.basket

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
import uz.example.onlineshop.ui.main.MainFragmentDirections

class BasketFragment: Fragment(R.layout.fragment_basket) {

    private lateinit var binding:FragmentBasketBinding
    private lateinit var navController: NavController
    private val adapter: BasketAdapter by inject()
    private var sum = 0L
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
            Product("Куртка", "400000",false),
            Product("Джинсы","123400",true),
            Product("Джинсы","123400",true),
            Product("Куртка", "400000",false),
            Product("Куртка", "400000",false),
            Product("Джинсы","123400",false),
            Product("Джинсы","123400",false),
            Product("Куртка", "400000",false),
            Product("Куртка", "400000",false),
            Product("Джинсы","123400",false),
            Product("Джинсы","123400",true),
            Product("Куртка", "400000",false)
        )

        for (i in adapter.models.indices){
            sum += adapter.models[i].price.toLong()
        }
        binding.btnOrder.text = "Заказать $sum uzs"
        adapter.onClickItem {
            val gsonPretty = GsonBuilder().setPrettyPrinting().create()
            val gsonString = gsonPretty.toJson(it)
            findNavController().navigate(BasketFragmentDirections.actionBasketFragmentToDetailFragment(gsonString))
        }
    }
}