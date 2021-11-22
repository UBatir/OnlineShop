package uz.example.onlineshop.ui.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.SimpleExpandableListAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.GsonBuilder
import org.koin.android.ext.android.inject
import uz.example.onlineshop.R
import uz.example.onlineshop.core.extentions.onClick
import uz.example.onlineshop.data.remote.Product
import uz.example.onlineshop.databinding.DialogBottomSheetBinding
import uz.example.onlineshop.databinding.FragmentBasketBinding


class BasketFragment: Fragment(R.layout.fragment_basket) {

    private lateinit var binding:FragmentBasketBinding
    private lateinit var navController: NavController
    private val adapter: BasketAdapter by inject()
    private var sum = 0L

    private val mGroupsArray = arrayListOf( "Способ оплаты")

    private val mWinterMonthsArray = arrayListOf ( "Наличные", "Карта")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentBasketBinding.bind(view)
        navController=Navigation.findNavController(view)
        binding.apply {
            btnBack.onClick {
                navController.popBackStack()
            }
            recyclerView.adapter=adapter
        }
        adapter.models= listOf(
            Product("Куртка", "400000",false),
            Product("Джинсы","123400",true),
            Product("Джинсы","123400",true),
            Product("Куртка", "400000",false),
            Product("Куртка", "400000",false),
            Product("Джинсы","123400",false),
            Product("Джинсы","123400",false),
            Product("Куртка", "400000",false),
            Product("Куртка", "400000",false),
            Product("Джинсы","123400",false),
            Product("Джинсы","123400",true),
            Product("Куртка", "400000",false)
        )

        for (i in adapter.models.indices){
            sum += adapter.models[i].price.toLong()
        }
        binding.btnOrder.text = "Заказать $sum uzs"
        adapter.onClickItem {
            val gsonPretty = GsonBuilder().setPrettyPrinting().create()
            val gsonString = gsonPretty.toJson(it)
            findNavController().navigate(BasketFragmentDirections.actionBasketFragmentToDetailFragment(gsonString))
        }
        binding.btnOrder.onClick{
            val dialog = BottomSheetDialog(requireContext())
            val bottomView = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_sheet, null)
            val bindingD=DialogBottomSheetBinding.bind(bottomView)


            var map: MutableMap<String?, String?>
            val groupDataList: ArrayList<Map<String?, String?>> = ArrayList()
            for (group in mGroupsArray) {
                map = HashMap()
                map["groupName"] = group
                groupDataList.add(map)
            }
            val groupFrom = arrayOf("groupName")
            val groupTo = intArrayOf(android.R.id.text1)
            val сhildDataList: ArrayList<ArrayList<Map<String?, String?>?>> = ArrayList()
            val сhildDataItemList: ArrayList<Map<String?, String?>?> = ArrayList()
            for (month in mWinterMonthsArray) {
                map = HashMap()
                map["monthName"] = month // название месяца
                сhildDataItemList.add(map)
            }
            сhildDataList.add(сhildDataItemList)
            val childFrom = arrayOf("monthName")
            val childTo = intArrayOf(R.id.rdButton)

            val adapter = SimpleExpandableListAdapter(
                requireContext(), groupDataList,
                android.R.layout.simple_expandable_list_item_1, groupFrom,
                groupTo, сhildDataList, R.layout.radiobutton_list_item_1,
                childFrom, childTo
            )
            bindingD.btnSelectCart.setAdapter(adapter)

            bindingD.btnSelectCart.setOnItemClickListener { parent, view, position, id ->

            }
            dialog.setContentView(bottomView)
            dialog.show()
        }
    }
}