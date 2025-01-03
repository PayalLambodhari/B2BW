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
        composable("login") {
            LoginScreen(navController = navController) {
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            }
        }

        composable("home") { HomeScreen(navController, cartItemCount) }

        composable("cart") {
            CartScreen(navController, cartItemCount) { updatedCartItemCount ->
                cartItemCount = updatedCartItemCount as Int
            }
        }

        composable("profile") { ProfileScreen(navController) }

        composable("foodShop") { FoodShopScreen(navController) }
        composable("clothesShop") { ClothesShopScreen(navController) }
        composable("vesselsShop") { VesselsShopScreen(navController) }
    }
}

@Composable
fun <NavHostController> HomeScreen(navController: NavHostController, cartItemCount: Int) {

}
