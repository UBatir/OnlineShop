package uz.example.onlineshop.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import uz.example.onlineshop.R
import uz.example.onlineshop.core.extentions.onClick
import uz.example.onlineshop.databinding.FragmentSplashBinding

class SplashFragment: Fragment(R.layout.fragment_splash) {

    private lateinit var binding: FragmentSplashBinding
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentSplashBinding.bind(view)
        navController=Navigation.findNavController(view)
        binding.apply {
            btnSignIn.onClick {
                navController.navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
            }
            btnSignUp.onClick {
                navController.navigate(SplashFragmentDirections.actionSplashFragmentToRegistrationFragment())
            }
        }
    }
}