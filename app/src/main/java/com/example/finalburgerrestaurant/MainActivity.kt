package com.example.finalburgerrestaurant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.finalburgerrestaurant.ui.theme.FinalburgerrestaurantTheme
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.finalburgerrestaurant.ui.theme.FinalburgerrestaurantTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinalburgerrestaurantTheme {
                BurgerApp()
            }
        }
    }
}

@Composable
fun BurgerApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(navController = navController) },
        content = { paddingValues ->   // <-- Here’s paddingValues
            androidx.compose.foundation.layout.Box(
                modifier = Modifier.padding(paddingValues) // Apply the padding here!
            ) {
                AppNavigation(navController = navController)
            }
        }
    )
}

