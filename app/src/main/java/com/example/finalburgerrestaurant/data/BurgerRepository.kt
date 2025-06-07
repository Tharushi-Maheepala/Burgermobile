package com.example.finalburgerrestaurant.data.repository

import android.content.Context
import com.example.finalburgerrestaurant.data.network.RetrofitInstance
import com.example.finalburgerrestaurant.data.util.NetworkUtils
import com.example.finalburgerrestaurant.model.Burger
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BurgerRepository(private val context: Context) {

    private val prefs = context.getSharedPreferences("burger_cache", Context.MODE_PRIVATE)

    suspend fun getBurgers(): List<Burger> {
        return if (NetworkUtils.isNetworkAvailable(context)) {
            val burgers = RetrofitInstance.api.getBurgers()
            // Cache JSON locally
            val jsonString = Gson().toJson(burgers)
            prefs.edit().putString("cached_burgers", jsonString).apply()
            burgers
        } else {
            // Load from local storage
            val json = prefs.getString("cached_burgers", null)
            if (json != null) {
                val type = object : TypeToken<List<Burger>>() {}.type
                Gson().fromJson(json, type)
            } else {
                emptyList() // or return a default list
            }
        }
    }

    fun isOffline(): Boolean {
        return !NetworkUtils.isNetworkAvailable(context)
    }
}