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
  lateinit var binding: FragmentProductPageBinding
  private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentProductPageBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // تنظیم کلیک برای بازگشت به لیست دسته‌بندی‌ها
        binding.iconCategoryCar.setOnClickListener {
            findNavController().navigate(R.id.action_productPage_to_homeScreen)
        }

        // تنظیم کلیک برای نمایش جزئیات سبد خرید
        binding.CartDetail.setOnClickListener {
            findNavController().navigate(R.id.action_productPage_to_productWarenkorb)
        }

        // تنظیم انیمیشن برای دکمه‌های forwardPage و backPage
        val slideInRight = AnimationUtils.loadAnimation(requireContext(), R.anim.slid_in_right)
        val slideOutLeft = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_out_left)
        val slideInLeft = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_fade_in)
        val slideOutRight = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_out_left)

        binding.forwardPage.setOnClickListener {
            // اجرای انیمیشن
            TransitionManager.beginDelayedTransition(binding.root)
            binding.productImageCart.startAnimation(slideInLeft)
            binding.productNamePage.startAnimation(slideInLeft)
            binding.DescriptionPage.startAnimation(slideInLeft)
            binding.productPreisPage.startAnimation(slideInLeft)
            viewModel.showNextSubcategory() // نمایش زیرمجموعه بعدی
        }

        binding.backPage.setOnClickListener {
            // اجرای انیمیشن
            TransitionManager.beginDelayedTransition(binding.root)
            binding.productImageCart.startAnimation(slideInLeft)
            binding.productNamePage.startAnimation(slideInLeft)
            binding.DescriptionPage.startAnimation(slideInLeft)
            binding.productPreisPage.startAnimation(slideInLeft)
            viewModel.showBackSubcategory() // نمایش زیرمجموعه قبلی
        }

        // مشاهده تغییرات در زیرمجموعه انتخاب شده
        viewModel.selectedSubcategory.observe(viewLifecycleOwner) { subcategory ->
            // تنظیم تصویر زیرمجموعه
            binding.productImageCart.setImageResource(subcategory.image)
            // تنظیم نام زیرمجموعه
            binding.productNamePage.text = subcategory.name
            // تنظیم توضیحات زیرمجموعه
            binding.DescriptionPage.text = subcategory.productDescription
            // تنظیم قیمت زیرمجموعه
            binding.productPreisPage.text = String.format("%.2f €", subcategory.price)

            // تنظیم کلیک برای افزودن محصول به سبد خرید
            binding.AddToCartPage.setOnClickListener {
                val warenkorbItem = Warenkorb(
                    productName = subcategory.name, // نام محصول
                    productDescription = subcategory.productDescription, // توضیحات محصول
                    productPrice = subcategory.price, // قیمت محصول
                    productImage = subcategory.image, // تصویر محصول
                    productQuantity = 1 // تعداد محصول
                )
                viewModel.addToCart(warenkorbItem) // افزودن محصول به سبد خرید
            }
        }

        // مشاهده تغییرات در تعداد آیتم‌های سبد خرید
        viewModel.itemCount.observe(viewLifecycleOwner) { count ->
            binding.WarenkorbStuk.text = count.toString() // نمایش تعداد آیتم‌های سبد خرید
        }
    }
}

