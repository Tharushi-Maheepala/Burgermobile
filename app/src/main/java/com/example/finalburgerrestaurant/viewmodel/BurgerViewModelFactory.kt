package com.example.finalburgerrestaurant.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalburgerrestaurant.data.repository.BurgerRepository

class BurgerViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BurgerViewModel(app) as T
    }
}