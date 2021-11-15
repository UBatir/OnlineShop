package uz.example.onlineshop.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.tabs.TabLayoutMediator
import uz.example.onlineshop.R
import uz.example.onlineshop.databinding.FragmentMainBinding
import uz.example.onlineshop.ui.main.popular.PopularFragment

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        navController = Navigation.findNavController(view)
        val fragmentList = arrayListOf<Fragment>(
            PopularFragment()
        )
        val adapter =
            ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)
        binding.apply {
            viewPager.adapter = adapter
            val tabLayoutMediator = TabLayoutMediator(tabLayout, viewPager) { tab, pos ->
                when (pos) {
                    0 -> tab.text = "Популярные"
                    1 -> tab.text = "Кетегории"
                   // 2 -> tab.text = "Акции"
                }
            }
            tabLayoutMediator.attach()
        }
    }
}