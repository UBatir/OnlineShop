package uz.example.onlineshop.ui.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import org.koin.android.ext.android.inject
import uz.example.onlineshop.R
import uz.example.onlineshop.databinding.FragmentMainBinding
import uz.example.onlineshop.ui.main.popular.PopularAdapter


class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var mNavController: NavController


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        navController = requireActivity().findNavController(R.id.main_nav_container)
        mNavController=Navigation.findNavController(view)
        binding.apply {
            appBarConfiguration = AppBarConfiguration(
                setOf(R.id.productFragment,
                    R.id.orderFragment, R.id.delivery_address,R.id.payment_method,
                    R.id.settings,R.id.exit
                ), drawerLayout
            )
            navView.setupWithNavController(navController)
            appBarMain.toolbar.setNavigationOnClickListener {
                navController.navigateUp(appBarConfiguration)
            }
            appBarMain.toolbar.setOnMenuItemClickListener {menuItem ->
                when(menuItem.itemId){
                    R.id.search -> {
                        mNavController.navigate(MainFragmentDirections.actionMainFragmentToSearchFragment())
                        true
                    }
                    R.id.favorite->{
                        mNavController.navigate(MainFragmentDirections.actionMainFragmentToFavoriteFragment())
                        true
                    }
                    R.id.basket->{
                        mNavController.navigate(MainFragmentDirections.actionMainFragmentToBasketFragment())
                        true
                    }
                    R.id.notification->{
                        true
                    }
                    else-> false
                }
            }
           navView.menu.findItem(R.id.payment_method).setOnMenuItemClickListener { menuItem ->
                if (menuItem.itemId==R.id.payment_method){
                    findNavController().navigate(MainFragmentDirections.actionMainFragmentToCardFragment())
                }
                true
            }
            navView.menu.findItem(R.id.delivery_address).setOnMenuItemClickListener { menuItem ->
                if (menuItem.itemId==R.id.delivery_address){
                    Toast.makeText(requireContext(), "address", Toast.LENGTH_SHORT).show()
                }
                true
            }
            navView.menu.findItem(R.id.settings).setOnMenuItemClickListener { menuItem ->
                if (menuItem.itemId==R.id.settings){
                    Toast.makeText(requireContext(), "settings", Toast.LENGTH_SHORT).show()
                }
                true
            }

            navView.menu.findItem(R.id.exit).setOnMenuItemClickListener { menuItem ->
                if (menuItem.itemId==R.id.exit){
                    findNavController().navigate(MainFragmentDirections.actionMainFragmentToLoginFragment())
                }
                true
            }

        }
    }
}