package uz.example.onlineshop.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import uz.example.onlineshop.R
import uz.example.onlineshop.core.extentions.onClick
import uz.example.onlineshop.databinding.FragmentRegistrationBinding

class RegistrationFragment:Fragment(R.layout.fragment_registration) {

    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentRegistrationBinding.bind(view)
        navController=Navigation.findNavController(view)
        binding.apply {
            btnBack.onClick {
                navController.popBackStack()
            }
            tvSignIn.onClick {
                navController.navigate(RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment())
            }
            btnSignIn.onClick {
                if(etPhoneNumber.text.isEmpty()||actvName.text.isEmpty()) {
                    if (etPhoneNumber.text.isEmpty()) etPhoneNumber.error=getString(R.string.input_your_number)
                    else if(actvName.text.isEmpty()) actvName.error=getString(R.string.input_your_name)
                }
                else navController.navigate(RegistrationFragmentDirections.actionRegistrationFragmentToMainFragment())
            }
        }

    }
}