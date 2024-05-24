package com.example.mehrdad_food_hub.Ui

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mehrdad_food_hub.Model.Subcategory
import com.example.mehrdad_food_hub.R
import com.example.mehrdad_food_hub.ViewModel.SharedViewModel
import com.example.mehrdad_food_hub.databinding.FragmentAddProductToListBinding


class AddProductToList : Fragment() {

    // تعریف بایندینگ برای فرگمنت
    // Binding für das Fragment definieren
    lateinit var binding: FragmentAddProductToListBinding

    // دریافت ViewModel به صورت activityViewModels
    // Das ViewModel als activityViewModels abrufen
    private val viewModel: SharedViewModel by activityViewModels()

    // ایجاد ویو فرگمنت
    // Die View des Fragments erstellen
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddProductToListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    // تنظیمات ویو پس از ایجاد
    // Einstellungen der View nach der Erstellung
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // تنظیم کلیک برای دکمه بازگشت
        // Klick-Listener für den Zurück-Button einstellen
        binding.backAddProduct.setOnClickListener {
            findNavController().navigate(R.id.action_addProductToList_to_homeScreen2)
        }

        // تنظیم کلیک برای دکمه افزودن محصول
        // Klick-Listener für den Produkt-Hinzufügen-Button einstellen
        binding.btnAddProduct.setOnClickListener {
            val name = binding.etProductName.text.toString()
            val description = binding.etProductDescription.text.toString()
            val price = binding.etProductPrice.text.toString().toDoubleOrNull() ?: 0.0
            val categoryName = binding.etCategoryName.text.toString()

            // بررسی خالی نبودن فیلدها
            // Überprüfen, ob die Felder nicht leer sind
            if (name.isBlank() || description.isBlank() || categoryName.isBlank()) {
                showCustomToast("Bitte füllen Sie alle Felder aus.")
            } else {
                // ایجاد زیرمجموعه جدید
                // Neue Unterkategorie erstellen
                val newSubcategory = Subcategory(
                    name = name,
                    productDescription = description,
                    price = price,
                    image = R.drawable.veggie_2 // باید تصویر مناسب را اضافه کنید // Das passende Bild hinzufügen
                )

                // افزودن محصول به دسته‌بندی
                // Produkt zur Kategorie hinzufügen
                viewModel.addProductToCategory(categoryName, newSubcategory)
                findNavController().popBackStack() // برای بازگشت به فرگمنت قبلی // Um zum vorherigen Fragment zurückzukehren
            }
        }
    }

    // نمایش توست سفارشی
    // Benutzerdefinierten Toast anzeigen
    private fun showCustomToast(message: String) {
        val inflater = layoutInflater
        val layout: View = inflater.inflate(R.layout.custum_toast, null)

        val textView: TextView = layout.findViewById(R.id.toastMessage)
        textView.text = message

        with(Toast(requireContext())) {
            setGravity(Gravity.BOTTOM, 0, 1500)
            duration = Toast.LENGTH_SHORT
            view = layout
            show()
        }
    }
}




