package com.example.miniproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isLoggedIn by rememberSaveable { mutableStateOf(false) }
            AppNavigator(isLoggedIn) {
                isLoggedIn = true
            }
        }
    }
}

@Composable
fun AppNavigator(isLoggedIn: Boolean, onLoginSuccess: () -> Unit) {
    val navController: NavHostController = rememberNavController()
    var cartItemCount by remember { mutableIntStateOf(0) }
    var cart by remember { mutableStateOf<List<Product>>(emptyList()) }

    val onAddToCart: (Product) -> Unit = { product ->
        cart = cart + product
        cartItemCount = cart.size
    }

    // Mock checkout function
    val onCheckout: () -> Unit = {
        // Implement your checkout logic here
        println("Proceeding to checkout with ${cart.size} items")
    }

    if (isLoggedIn) {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(navController, cartItemCount)
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(Screen.Home.route) {
                    HomeScreen(navController) // Pass navController to HomeScreen
                }
                composable("foodShop") {
                    FoodShopScreen(navController, onAddToCart) // Pass onAddToCart to FoodShopScreen
                }
                composable("clothesShop") {
                    ClothesShopScreen(navController, onAddToCart) // Pass onAddToCart to ClothesShopScreen
                }
                composable("vesselsShop") {
                    VesselsShopScreen(navController, onAddToCart) // Pass onAddToCart to VesselsShopScreen
                }
                composable(Screen.Cart.route) {
                    CartScreen(navController, onCheckout) // Pass onCheckout to CartScreen
                }
                composable(Screen.Profile.route) {
                    ProfileScreen(navController)
                }
            }
        }
    } else {
        LoginScreen(navController, onLoginSuccess = {
            onLoginSuccess()
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Home.route) { inclusive = true }
            }
        })
    }
}
