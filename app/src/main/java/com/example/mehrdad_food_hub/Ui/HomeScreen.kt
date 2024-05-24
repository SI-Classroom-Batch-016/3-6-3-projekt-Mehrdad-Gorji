package com.example.mehrdad_food_hub.Ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mehrdad_food_hub.Adapter.CategoryAadapter
import com.example.mehrdad_food_hub.Datasource.Datasource
import com.example.mehrdad_food_hub.Model.Subcategory
import com.example.mehrdad_food_hub.R
import com.example.mehrdad_food_hub.ViewModel.SharedViewModel
import com.example.mehrdad_food_hub.databinding.FragmentHomeScreenBinding


class HomeScreen : Fragment() {
    // تعریف بایندینگ برای فرگمنت
    // Binding für das Fragment definieren
    lateinit var binding: FragmentHomeScreenBinding

    // تعریف ViewModel مشترک بین فرگمنت‌ها
    // Gemeinsames ViewModel zwischen den Fragmenten definieren
    private val viewModel: SharedViewModel by activityViewModels()

    // ایجاد ویو فرگمنت
    // Die View des Fragments erstellen
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    // تنظیمات ویو پس از ایجاد
    // Einstellungen der View nach der Erstellung
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // تنظیم کلیک برای دکمه افزودن محصول
        // Klick-Listener für den Produkt-Hinzufügen-Button einstellen
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreen_to_addProductToList)
        }

        // تعریف تابع برای کلیک روی دسته‌بندی
        // Funktion für Klick auf die Kategorie definieren
        val onCategoryClick: (List<Subcategory>, String) -> Unit = { subcategories, categoryName ->
            viewModel.setSubcategories(subcategories)
            val action = HomeScreenDirections.actionHomeScreenToHomeSubcategory(categoryName)
            Handler(Looper.getMainLooper()).postDelayed({
                findNavController().navigate(action)
            }, 200) // 200 milliseconds delay
        }

        // مشاهده تغییرات در دسته‌بندی‌ها
        // Beobachten von Änderungen in den Kategorien
        viewModel.categories.observe(viewLifecycleOwner) { categories ->
            val sortedCategories = categories.sortedBy { it.name }
            val adapter = CategoryAadapter(sortedCategories) { subcategories, categoryName ->
                onCategoryClick(subcategories, categoryName)
            }
            binding.RecyclerViewCategory.adapter = adapter
        }

        // تنظیم آداپتور برای RecyclerView
        // Adapter für RecyclerView einstellen
        val adapter = CategoryAadapter(Datasource().categories()) { subcategories, categoryName ->
            onCategoryClick(subcategories, categoryName)
        }
        binding.RecyclerViewCategory.adapter = adapter
    }
}