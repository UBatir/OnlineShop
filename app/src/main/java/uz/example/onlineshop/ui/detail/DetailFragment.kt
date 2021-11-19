package uz.example.onlineshop.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import uz.example.onlineshop.R
import uz.example.onlineshop.core.extentions.onClick
import uz.example.onlineshop.data.remote.Product
import uz.example.onlineshop.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding:FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val safeArgs: DetailFragmentArgs by navArgs()
    private lateinit var product:Product

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val gson = Gson()
        product = gson.fromJson(safeArgs.product, Product::class.java)
        binding.apply {
            btnBack.onClick {
                navController.popBackStack()
            }
            tvTitle.text = product.name
            tvPrice.text = product.price
        }
    }
}