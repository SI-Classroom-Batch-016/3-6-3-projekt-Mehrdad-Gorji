package com.example.mehrdad_food_hub.Ui

import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mehrdad_food_hub.Model.Warenkorb
import com.example.mehrdad_food_hub.R
import com.example.mehrdad_food_hub.ViewModel.SharedViewModel
import com.example.mehrdad_food_hub.databinding.FragmentProductPageBinding

class ProductPage : Fragment() {
    // تعریف بایندینگ برای فرگمنت
    // Binding für das Fragment definieren
    lateinit var binding: FragmentProductPageBinding

    // تعریف ViewModel مشترک بین فرگمنت‌ها
    // Gemeinsames ViewModel zwischen den Fragmenten definieren
    private val viewModel: SharedViewModel by activityViewModels()

    // ایجاد ویو فرگمنت
    // Die View des Fragments erstellen
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    // تنظیمات ویو پس از ایجاد
    // Einstellungen der View nach der Erstellung
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // تنظیم کلیک برای بازگشت به لیست دسته‌بندی‌ها
        // Klick-Listener für den Zurück-Button zu den Kategorien einstellen
        binding.iconCategoryCar.setOnClickListener {
            findNavController().navigate(R.id.action_productPage_to_homeScreen)
        }

        // تنظیم کلیک برای نمایش جزئیات سبد خرید
        // Klick-Listener für die Anzeige der Warenkorbdetails einstellen
        binding.CartDetail.setOnClickListener {
            findNavController().navigate(R.id.action_productPage_to_productWarenkorb)
        }

        // تنظیم انیمیشن برای دکمه‌های forwardPage و backPage
        // Animation für die Buttons forwardPage und backPage einstellen
        val slideInRight = AnimationUtils.loadAnimation(requireContext(), R.anim.slid_in_right)
        val slideOutLeft = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_out_left)
        val slideInLeft = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_fade_in)
        val slideOutRight = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_out_left)

        binding.forwardPage.setOnClickListener {
            // اجرای انیمیشن
            // Animation ausführen
            TransitionManager.beginDelayedTransition(binding.root)
            binding.productImageCart.startAnimation(slideInLeft)
            binding.productNamePage.startAnimation(slideInLeft)
            binding.DescriptionPage.startAnimation(slideInLeft)
            binding.productPreisPage.startAnimation(slideInLeft)
            viewModel.showNextSubcategory() // نمایش زیرمجموعه بعدی
            // Nächste Unterkategorie anzeigen
        }

        binding.backPage.setOnClickListener {
            // اجرای انیمیشن
            // Animation ausführen
            TransitionManager.beginDelayedTransition(binding.root)
            binding.productImageCart.startAnimation(slideInLeft)
            binding.productNamePage.startAnimation(slideInLeft)
            binding.DescriptionPage.startAnimation(slideInLeft)
            binding.productPreisPage.startAnimation(slideInLeft)
            viewModel.showBackSubcategory() // نمایش زیرمجموعه قبلی
            // Vorherige Unterkategorie anzeigen
        }

        // مشاهده تغییرات در زیرمجموعه انتخاب شده
        // Beobachten von Änderungen in der ausgewählten Unterkategorie
        viewModel.selectedSubcategory.observe(viewLifecycleOwner) { subcategory ->
            // تنظیم تصویر زیرمجموعه
            // Bild der Unterkategorie festlegen
            binding.productImageCart.setImageResource(subcategory.image)
            // تنظیم نام زیرمجموعه
            // Name der Unterkategorie festlegen
            binding.productNamePage.text = subcategory.name
            // تنظیم توضیحات زیرمجموعه
            // Beschreibung der Unterkategorie festlegen
            binding.DescriptionPage.text = subcategory.productDescription
            // تنظیم قیمت زیرمجموعه
            // Preis der Unterkategorie festlegen
            binding.productPreisPage.text = String.format("%.2f €", subcategory.price)

            // تنظیم کلیک برای افزودن محصول به سبد خرید
            // Klick-Listener zum Hinzufügen des Produkts zum Warenkorb einstellen
            binding.AddToCartPage.setOnClickListener {
                val warenkorbItem = Warenkorb(
                    productName = subcategory.name, // نام محصول
                    // Produktname
                    productDescription = subcategory.productDescription, // توضیحات محصول
                    // Produktbeschreibung
                    productPrice = subcategory.price, // قیمت محصول
                    // Produktpreis
                    productImage = subcategory.image, // تصویر محصول
                    // Produktbild
                    productQuantity = 1 // تعداد محصول
                    // Produktmenge
                )
                // افزودن محصول به سبد خرید
                // Produkt zum Warenkorb hinzufügen
                viewModel.addToCart(warenkorbItem)

            }
        }

        // مشاهده تغییرات در تعداد آیتم‌های سبد خرید
        // Beobachten von Änderungen in der Anzahl der Warenkorb-Artikel
        viewModel.itemCount.observe(viewLifecycleOwner) { count ->
            // نمایش تعداد آیتم‌های سبد خرید
            // Anzahl der Warenkorb-Artikel anzeigen
            binding.WarenkorbStuk.text = count.toString()

        }
    }
}