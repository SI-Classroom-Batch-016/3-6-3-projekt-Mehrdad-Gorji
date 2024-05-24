package com.example.mehrdad_food_hub.Ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mehrdad_food_hub.Adapter.WarenkorbAdapter
import com.example.mehrdad_food_hub.Model.Warenkorb
import com.example.mehrdad_food_hub.R
import com.example.mehrdad_food_hub.ViewModel.SharedViewModel
import com.example.mehrdad_food_hub.databinding.FragmentProductWarenkorbBinding


class ProductWarenkorb : Fragment() {
    lateinit var binding: FragmentProductWarenkorbBinding
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductWarenkorbBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // تنظیم کلیک برای بازگشت به صفحه جزئیات محصول
        binding.backToDeteil.setOnClickListener {
            findNavController().navigate(R.id.action_productWarenkorb_to_homeScreen)
        }

        // تنظیم LayoutManager برای recyclerViewWarenkorb
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewWarenkorb.layoutManager = layoutManager

        // تعریف تابعی برای مدیریت تغییر تعداد محصولات
        val onQuantityChange: (Warenkorb, Int) -> Unit = { warenkorb, quantity ->
            viewModel.updateQuantity(warenkorb, quantity)
        }

        // تعریف تابعی برای مدیریت حذف محصولات
        val onItemRemove: (Warenkorb) -> Unit = { item ->
            viewModel.removeItemFromCart(item)
        }

        // مشاهده تغییرات در لیست سبد خرید و بایند کردن آنها به RecyclerView
        viewModel.cart.observe(viewLifecycleOwner) { cart ->
            // مرتب‌سازی سبد خرید بر اساس حروف الفبا
            val sortedCart = cart.sortedBy { it.productName }

            // ایجاد آداپتر برای سبد خرید
            val adapter = WarenkorbAdapter(sortedCart, onQuantityChange, onItemRemove)
            binding.recyclerViewWarenkorb.adapter = adapter

            // محاسبه و نمایش قیمت کل سبد خرید
            val totalPrice = sortedCart.sumOf { it.productPrice * it.productQuantity }
            binding.gesamtTotalWarenkorb.text = String.format("%.2f €", totalPrice)
        }
    }
}
