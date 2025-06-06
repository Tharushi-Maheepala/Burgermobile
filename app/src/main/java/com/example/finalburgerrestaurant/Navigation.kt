package com.example.finalburgerrestaurant

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalburgerrestaurant.ui.MenuScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "signup"  // or "splash" if you want to start with splash screen
    ) {
        composable("home") { HomeScreen() }
        composable("menu") { MenuScreen() }
        composable("cart") { CartScreen() }
        composable("profile") { ProfileScreen(navController) }

        composable("splash") { SplashScreen() }

        // ✅ Fix: Pass navController to these screens
        composable("login") { LoginScreen(navController) }
        composable("signup") { SignUpScreen(navController) }

        composable("details") { DetailsScreen() }
        composable("checkout") { CheckoutScreen() }
    }
}