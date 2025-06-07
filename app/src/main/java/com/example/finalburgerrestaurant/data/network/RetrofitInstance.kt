package com.example.finalburgerrestaurant.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: BurgerApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/Tharushi-Maheepala/Burgermobile/main/app/src/main/assets/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BurgerApi::class.java)
    }
}