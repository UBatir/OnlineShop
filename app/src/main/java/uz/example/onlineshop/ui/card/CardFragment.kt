package uz.example.onlineshop.ui.card

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import uz.example.onlineshop.R
import uz.example.onlineshop.core.extentions.onClick
import uz.example.onlineshop.databinding.FragmentCardBinding
import uz.example.onlineshop.databinding.FragmentPopularBinding

class CardFragment : Fragment(R.layout.fragment_card) {
    private lateinit var binding: FragmentCardBinding
    lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCardBinding.bind(view)
        navController = Navigation.findNavController(view)
        binding.btnBack.onClick {
            navController.popBackStack()
        }
    }
}