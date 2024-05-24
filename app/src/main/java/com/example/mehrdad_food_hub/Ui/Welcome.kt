package com.example.mehrdad_food_hub.Ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mehrdad_food_hub.R
import com.example.mehrdad_food_hub.databinding.FragmentWelcomeBinding


class Welcome : Fragment() {

    // تعریف بایندینگ برای فرگمنت
    // Binding für das Fragment definieren
    lateinit var binding: FragmentWelcomeBinding

    // ایجاد ویو فرگمنت
    // Die View des Fragments erstellen
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    // تنظیمات ویو پس از ایجاد
    // Einstellungen der View nach der Erstellung
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // انتقال به فرگمنت صفحه اصلی پس از تاخیر
        // Navigation zum HomeScreen-Fragment nach einer Verzögerung
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_welcome_to_homeScreen)
        }, 4000) // 4 seconds delay
    }
}