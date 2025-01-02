package com.example.miniproject

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    var cartItemCount by remember { mutableIntStateOf(0) }

    NavHost(navController, startDestination = "login") {
        // Login Screen
        composable("login") {
            LoginScreen(navController = navController) {
                // Proceed to home after login
                navController.navigate("home") {
                    // Clearing login from back stack is optional depending on the navigation structure
                    popUpTo("login") { inclusive = true }  // Clear login from back stack if needed
                }
            }
        }

        // Home Screen
        composable("home") { HomeScreen(navController, cartItemCount) }

        // Cart Screen
        composable("cart") {
            CartScreen(navController, cartItemCount) { updatedCartItemCount ->
                cartItemCount = updatedCartItemCount as Int
            }
        }

        // Profile Screen
        composable("profile") { ProfileScreen(navController) }

        // Shop Screens
        composable("foodShop") { FoodShopScreen(navController) }
        composable("clothesShop") { ClothesShopScreen(navController) }
        composable("vesselsShop") { VesselsShopScreen(navController) }
    }
}