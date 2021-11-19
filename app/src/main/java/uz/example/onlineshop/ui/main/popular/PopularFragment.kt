package uz.example.onlineshop.ui.main.popular

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.gson.GsonBuilder
import org.koin.android.ext.android.inject
import uz.example.onlineshop.R
import uz.example.onlineshop.data.remote.Product
import uz.example.onlineshop.databinding.FragmentPopularBinding
import uz.example.onlineshop.ui.main.MainFragmentDirections


class PopularFragment : Fragment(R.layout.fragment_popular) {

    private lateinit var binding: FragmentPopularBinding
    private val adapter: PopularAdapter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPopularBinding.bind(view)
        binding.apply {
            recyclerView.adapter = adapter
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
        adapter.onClickItem {
            val gsonPretty = GsonBuilder().setPrettyPrinting().create()
            val gsonString = gsonPretty.toJson(it)
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(gsonString))
        }
    }
}