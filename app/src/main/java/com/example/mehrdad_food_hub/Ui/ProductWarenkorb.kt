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
    // تعریف بایندینگ برای فرگمنت
    // Binding für das Fragment definieren
    lateinit var binding: FragmentProductWarenkorbBinding

    // تعریف ViewModel مشترک بین فرگمنت‌ها
    // Gemeinsames ViewModel zwischen den Fragmenten definieren
    private val viewModel: SharedViewModel by activityViewModels()

    // ایجاد ویو فرگمنت
    // Die View des Fragments erstellen
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductWarenkorbBinding.inflate(inflater, container, false)
        return binding.root
    }

    // تنظیمات ویو پس از ایجاد
    // Einstellungen der View nach der Erstellung
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // تنظیم کلیک برای بازگشت به صفحه جزئیات محصول
        // Klick-Listener für den Zurück-Button zur Produktdetailseite einstellen
        binding.backToDeteil.setOnClickListener {
            findNavController().navigate(R.id.action_productWarenkorb_to_homeScreen)
        }

        // تنظیم LayoutManager برای recyclerViewWarenkorb
        // LayoutManager für recyclerViewWarenkorb einstellen
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewWarenkorb.layoutManager = layoutManager

        // تعریف تابعی برای مدیریت تغییر تعداد محصولات
        // Funktion zur Verwaltung der Änderung der Produktmengen definieren
        val onQuantityChange: (Warenkorb, Int) -> Unit = { warenkorb, quantity ->
            viewModel.updateQuantity(warenkorb, quantity)
        }

        // تعریف تابعی برای مدیریت حذف محصولات
        // Funktion zur Verwaltung der Entfernung von Produkten definieren
        val onItemRemove: (Warenkorb) -> Unit = { item ->
            viewModel.removeItemFromCart(item)
        }

        // مشاهده تغییرات در لیست سبد خرید و بایند کردن آنها به RecyclerView
        // Beobachten von Änderungen in der Warenkorbliste und deren Bindung an RecyclerView
        viewModel.cart.observe(viewLifecycleOwner) { cart ->
            // مرتب‌سازی سبد خرید بر اساس حروف الفبا
            // Den Warenkorb alphabetisch sortieren
            val sortedCart = cart.sortedBy { it.productName }

            // ایجاد آداپتر برای سبد خرید
            // Adapter für den Warenkorb erstellen
            val adapter = WarenkorbAdapter(sortedCart, onQuantityChange, onItemRemove)
            binding.recyclerViewWarenkorb.adapter = adapter

            // محاسبه و نمایش قیمت کل سبد خرید
            // Gesamtkosten des Warenkorbs berechnen und anzeigen
            val totalPrice = sortedCart.sumOf { it.productPrice * it.productQuantity }
            binding.gesamtTotalWarenkorb.text = String.format("%.2f €", totalPrice)
        }
    }
}