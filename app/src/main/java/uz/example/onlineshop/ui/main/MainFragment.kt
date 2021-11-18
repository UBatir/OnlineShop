package uz.example.onlineshop.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import uz.example.onlineshop.R
import uz.example.onlineshop.databinding.FragmentMainBinding


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
                    R.id.orderFragment, R.id.favorite, R.id.delivery_address,R.id.payment_method,R.id.notification,
                    R.id.reference, R.id.about, R.id.settings,R.id.exit
                ), drawerLayout
            )
            navView.setupWithNavController(navController)
            appBarMain.toolbar.setNavigationOnClickListener {
                navController.navigateUp(appBarConfiguration)
            }
            appBarMain.toolbar.setOnMenuItemClickListener {menuItem ->
                when(menuItem.itemId){
                    R.id.search -> {
                        true
                    }
                    R.id.favorite->{
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
        }
    }

}