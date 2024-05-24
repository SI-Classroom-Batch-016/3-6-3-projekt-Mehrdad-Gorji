package com.example.mehrdad_food_hub.Model

data class Category(
    val name: String,
    val image: Int,
    val subcategories: List<Subcategory> // اضافه کردن لیست زیرمجموعه‌ها
)
