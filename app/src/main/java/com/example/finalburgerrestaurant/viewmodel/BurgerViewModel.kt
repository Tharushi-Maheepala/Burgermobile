package com.example.finalburgerrestaurant.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.compose.runtime.mutableStateListOf
import com.example.finalburgerrestaurant.data.BurgerRepository
import com.example.finalburgerrestaurant.model.Burger

class BurgerViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = BurgerRepository(application)

    val burgers = mutableStateListOf<Burger>()

    init {
        loadData()
    }

    private fun loadData() {
        burgers.clear()
        burgers.addAll(repository.loadBurgers())
    }
}