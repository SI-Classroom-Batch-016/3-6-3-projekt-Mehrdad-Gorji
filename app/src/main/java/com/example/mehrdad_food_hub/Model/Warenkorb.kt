package com.example.mehrdad_food_hub.Model

data class Warenkorb(

    val productName: String,
    val productDescription: String,
    val productPrice: Double,
    val productImage: Int,
    var productQuantity: Int,

)
