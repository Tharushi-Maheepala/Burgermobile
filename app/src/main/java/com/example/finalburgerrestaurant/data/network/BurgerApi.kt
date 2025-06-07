package com.example.finalburgerrestaurant.data.network

import com.example.finalburgerrestaurant.model.Burger
import retrofit2.http.GET

interface BurgerApi {
    @GET("burgers.json") //  Update path depending on your JSON file name
    suspend fun getBurgers(): List<Burger>
}