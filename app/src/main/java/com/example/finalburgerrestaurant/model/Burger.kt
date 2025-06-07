package com.example.finalburgerrestaurant.model


data class Burger(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val image: String,
    val category: String // 👈 make sure this exists!
)