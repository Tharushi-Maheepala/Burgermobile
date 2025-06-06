package com.example.finalburgerrestaurant.data

import android.content.Context
import com.example.finalburgerrestaurant.model.Burger
import com.google.gson.Gson

class BurgerRepository(private val context: Context) {

    fun loadBurgers(): List<Burger> {
        val json = context.assets.open("burgers.json").bufferedReader().use { it.readText() }
        val listType = object : com.google.gson.reflect.TypeToken<List<Burger>>() {}.type
        return Gson().fromJson(json, listType)
    }
}