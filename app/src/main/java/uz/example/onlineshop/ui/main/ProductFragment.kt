package uz.example.onlineshop.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.tabs.TabLayoutMediator
import uz.example.onlineshop.R
import uz.example.onlineshop.databinding.FragmentMainBinding
import uz.example.onlineshop.databinding.FragmentProductBinding
import uz.example.onlineshop.ui.main.category.CategoryFragment
import uz.example.onlineshop.ui.main.popular.PopularFragment

class ProductFragment : Fragment(R.layout.fragment_product) {

    private lateinit var binding: FragmentProductBinding
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductBinding.bind(view)
        navController = Navigation.findNavController(view)
        val fragmentList = arrayListOf(
            PopularFragment(),
            CategoryFragment()
        )
        val adapter =
            ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)
        binding.apply {
            viewPager.adapter = adapter
            val tabLayoutMediator = TabLayoutMediator(tabLayout, viewPager) { tab, pos ->
                when (pos) {
                    0 -> tab.text = "Популярные"
                    1 -> tab.text = "Кетегории"
                }
            }
            tabLayoutMediator.attach()
        }
    }
}