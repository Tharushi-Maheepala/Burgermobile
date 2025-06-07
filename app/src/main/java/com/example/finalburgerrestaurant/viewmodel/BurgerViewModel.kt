package com.example.finalburgerrestaurant.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.finalburgerrestaurant.data.repository.BurgerRepository
import com.example.finalburgerrestaurant.model.Burger
import kotlinx.coroutines.launch

class BurgerViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = BurgerRepository(application)

    val burgers = mutableStateListOf<Burger>()
    val isOffline = mutableStateOf(false)

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            try {
                val burgerList = repository.getBurgers()
                burgers.clear()
                burgers.addAll(burgerList)

                isOffline.value = repository.isOffline()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}