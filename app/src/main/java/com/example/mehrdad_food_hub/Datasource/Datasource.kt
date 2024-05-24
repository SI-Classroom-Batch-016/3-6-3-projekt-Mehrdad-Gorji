package com.example.mehrdad_food_hub.Datasource

import com.example.mehrdad_food_hub.Model.Category
import com.example.mehrdad_food_hub.Model.Subcategory
import com.example.mehrdad_food_hub.R

class Datasource {


    fun categories(): List<Category> {

        return listOf(

            Category("Pizza", R.drawable.bbq_chicken_pizza, listOf(
                Subcategory("Margherita", "Classic pizza with tomatoes and mozzarella", 8.99, R.drawable.margherita_),
                Subcategory("Pepperoni", "Pepperoni pizza with extra cheese", 9.99, R.drawable.pepperoni),
                Subcategory("BBQ Chicken", "BBQ chicken pizza with onions", 10.99, R.drawable.bbq_chicken_pizza),
                Subcategory("Hawaiian", "Pizza with ham and pineapple", 9.49, R.drawable.hawaiian),
                Subcategory("Veggie", "Pizza with various vegetables", 8.49, R.drawable.veggie),
                Subcategory("Meat Lovers", "Pizza with assorted meats", 11.99, R.drawable.meat_lovers),
                Subcategory("Cheese", "Pizza with extra cheese", 7.99, R.drawable.cheese),
                Subcategory("Buffalo Chicken", "Pizza with buffalo chicken and blue cheese", 10.49, R.drawable.margherita_),
                Subcategory("Spinach Alfredo", "Pizza with spinach and Alfredo sauce", 9.99, R.drawable.spinach_alfredo),
                Subcategory("Mushroom", "Pizza with mushrooms and garlic", 8.99, R.drawable.mushroom_pizza))),

            Category("Pasta", R.drawable.linguine, listOf(
                Subcategory("Spaghetti", "Spaghetti with marinara sauce", 7.99, R.drawable.linguine),
                Subcategory("Fettuccine", "Fettuccine with Alfredo sauce", 8.49, R.drawable.fettuccine),
                Subcategory("Lasagna", "Layered pasta with meat and cheese", 9.99, R.drawable.lasagna),
                Subcategory("Penne Arrabbiata", "Penne pasta with spicy tomato sauce", 8.99, R.drawable.penne_arrabbiata),
                Subcategory("Ravioli", "Ravioli stuffed with ricotta cheese", 9.49, R.drawable.ravioli),
                Subcategory("Tortellini", "Tortellini with pesto sauce", 9.99, R.drawable.tortellini),
                Subcategory("Macaroni", "Macaroni with cheddar cheese", 7.49, R.drawable.macaroni),
                Subcategory("Gnocchi", "Gnocchi with tomato basil sauce", 8.99, R.drawable.gnocchi),
                Subcategory("Linguine", "Linguine with clam sauce", 10.49, R.drawable.linguine),
                Subcategory("Tagliatelle", "Tagliatelle with bolognese sauce", 9.99, R.drawable.tagliatelle))),

            Category("Salad", R.drawable.caesar, listOf(
                Subcategory("Caesar", "Romaine lettuce with Caesar dressing", 6.99, R.drawable.caesar),
                Subcategory("Greek", "Salad with feta cheese and olives", 7.49, R.drawable.greek),
                Subcategory("Garden", "Mixed greens with various vegetables", 5.99, R.drawable.garden),
                Subcategory("Cobb", "Salad with chicken, bacon, and blue cheese", 8.99, R.drawable.cobb),
                Subcategory("Caprese", "Salad with tomatoes, mozzarella, and basil", 7.49, R.drawable.caesar),
                Subcategory("Quinoa", "Quinoa salad with vegetables", 8.49, R.drawable.quinoa100),
                Subcategory("Spinach", "Spinach salad with strawberries and nuts", 7.99, R.drawable.spinach),
                Subcategory("Kale", "Kale salad with lemon vinaigrette", 6.99, R.drawable.kale),
                Subcategory("Chickpea", "Chickpea salad with cucumber and mint", 7.49, R.drawable.chickpea),
                Subcategory("Arugula", "Arugula salad with Parmesan and lemon", 6.99, R.drawable.arugula))),

            Category("Desserts", R.drawable.ice_cream, listOf(
                Subcategory("Chocolate Cake", "Rich chocolate cake with frosting", 5.99, R.drawable.chocolate_cake),
                Subcategory("Cheesecake", "Creamy cheesecake with a graham cracker crust", 6.49, R.drawable.cheesecake),
                Subcategory("Brownie", "Fudgy brownies with walnuts", 4.99, R.drawable.brownie),
                Subcategory("Ice Cream", "Vanilla ice cream with chocolate sauce", 3.99, R.drawable.ice_cream),
                Subcategory("Tiramisu", "Italian dessert with coffee and mascarpone", 6.99, R.drawable.tiramisu),
                Subcategory("Panna Cotta", "Creamy Italian dessert with berries", 5.49, R.drawable.panna_cotta),
                Subcategory("Apple Pie", "Classic apple pie with cinnamon", 4.99, R.drawable.apple_pie_desert),
                Subcategory("Fruit Tart", "Tart with fresh fruit and custard", 5.99, R.drawable.fruit_tart),
                Subcategory("Macarons", "French almond cookies", 7.49, R.drawable.macarons),
                Subcategory("Donuts", "Glazed donuts with sprinkles", 2.99, R.drawable.donuts))),

            Category("Drinks", R.drawable.lemonade, listOf(
                Subcategory("Coca Cola", "Classic Coca Cola", 1.99, R.drawable.coca_cola),
                Subcategory("Pepsi", "Classic Pepsi", 1.99, R.drawable.pepsi),
                Subcategory("Lemonade", "Fresh lemonade", 2.49, R.drawable.lemonade),
                Subcategory("Iced Tea", "Iced tea with lemon", 2.49, R.drawable.iced_tea),
                Subcategory("Coffee", "Hot coffee", 1.99, R.drawable.coffee),
                Subcategory("Espresso", "Strong espresso", 2.99, R.drawable.espresso),
                Subcategory("Smoothie", "Fruit smoothie", 3.99, R.drawable.smoothie),
                Subcategory("Milkshake", "Chocolate milkshake", 3.49, R.drawable.milkshake),
                Subcategory("Water", "Bottled water", 0.99, R.drawable.water),
                Subcategory("Orange Juice", "Fresh orange juice", 2.99, R.drawable.orange_juice))),

            Category("Sandwiches", R.drawable.blt, listOf(
                Subcategory("Club Sandwich", "Turkey, bacon, and lettuce sandwich", 5.99, R.drawable.club_sandwich),
                Subcategory("BLT", "Bacon, lettuce, and tomato sandwich", 4.99, R.drawable.blt),
                Subcategory("Grilled Cheese", "Classic grilled cheese sandwich", 3.99, R.drawable.grilled_cheese),
                Subcategory("Ham & Cheese", "Ham and cheese sandwich", 4.49, R.drawable.ham___cheese),
                Subcategory("Tuna Salad", "Tuna salad sandwich", 4.99, R.drawable.tuna_salad)))
        )
    }
}
