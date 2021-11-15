package uz.example.onlineshop.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import uz.example.onlineshop.R
import uz.example.onlineshop.core.extentions.onClick
import uz.example.onlineshop.databinding.FragmentLoginBinding

class LoginFragment: Fragment(R.layout.fragment_login) {

    private lateinit var binding:FragmentLoginBinding
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentLoginBinding.bind(view)
        navController=Navigation.findNavController(view)
        binding.apply {
            btnSignIn.onClick {
                if(etPhoneNumber.text.isEmpty()) etPhoneNumber.error=getString(R.string.input_your_number)
                else navController.navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
            }
            btnBack.onClick {
                navController.popBackStack()
            }
            btnCreateAcc.onClick {
                navController.navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
            }
        }
    }
}