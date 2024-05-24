package com.example.mehrdad_food_hub.Ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mehrdad_food_hub.Adapter.SubcategoryAdapter
import com.example.mehrdad_food_hub.Model.Warenkorb
import com.example.mehrdad_food_hub.R
import com.example.mehrdad_food_hub.ViewModel.SharedViewModel
import com.example.mehrdad_food_hub.databinding.FragmentHomeSubcategoryBinding
import com.example.mehrdad_food_hub.databinding.FragmentProductPageBinding


class HomeSubcategory : Fragment() {
    // تعریف بایندینگ برای فرگمنت
    // Binding für das Fragment definieren
    lateinit var binding: FragmentHomeSubcategoryBinding

    // تعریف ViewModel مشترک بین فرگمنت‌ها
    // Gemeinsames ViewModel zwischen den Fragmenten definieren
    private val viewModel: SharedViewModel by activityViewModels()

    // ایجاد ویو فرگمنت
    // Die View des Fragments erstellen
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeSubcategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    // تنظیمات ویو پس از ایجاد
    // Einstellungen der View nach der Erstellung
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // دریافت نام دسته‌بندی از آرگومان‌ها
        // Den Kategorienamen aus den Argumenten abrufen
        val categoryName = arguments?.let { HomeSubcategoryArgs.fromBundle(it).categoryName }
        binding.SubShowText.text = categoryName

        // تنظیم کلیک برای دکمه بازگشت به دسته‌بندی‌ها
        // Klick-Listener für den Zurück-Button zu den Kategorien einstellen
        binding.backToCategory.setOnClickListener {
            Handler(Looper.getMainLooper()).postDelayed({
                findNavController().navigate(R.id.action_homeSubcategory_to_homeScreen)
            }, 200) // 200 milliseconds delay
        }

        // تنظیم LayoutManager برای RecyclerView
        // LayoutManager für RecyclerView einstellen
        binding.RecyclerViewSubcategory.layoutManager = LinearLayoutManager(requireContext())

        // مشاهده تغییرات در زیرمجموعه‌ها
        // Beobachten von Änderungen in den Unterkategorien
        viewModel.subcategories.observe(viewLifecycleOwner, Observer { subcategories ->
            val sortedSubcategories = subcategories.sortedBy { it.name }
            val subcategoryAdapter = SubcategoryAdapter(sortedSubcategories) { subcategory ->
                viewModel.setSelectedSubcategory(subcategory)
                findNavController().navigate(R.id.action_homeSubcategory_to_productPage)
            }
            // تنظیم آداپتور برای RecyclerView
            // Adapter für RecyclerView einstellen
            binding.RecyclerViewSubcategory.adapter = subcategoryAdapter
        })
    }
}