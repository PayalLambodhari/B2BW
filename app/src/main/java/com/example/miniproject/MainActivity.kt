package com.example.miniproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// MainActivity where navigation happens
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isLoggedIn by rememberSaveable { mutableStateOf(false) }
            AppNavigator(isLoggedIn) {
                isLoggedIn = false
            }
        }
    }
}

// AppNavigator with routing logic
@Composable
fun AppNavigator(isLoggedIn: Boolean, onLoginSuccess: () -> Unit) {
    val navController: NavHostController = rememberNavController()
    var cartItemCount by remember { mutableIntStateOf(0) }
    var cart by remember { mutableStateOf<List<Product>>(emptyList()) }

    val onAddToCart: (Product) -> Unit = { product ->
        cart = cart + product
        cartItemCount = cart.size
    }

    val onCheckout: () -> Unit = {
        println("Proceeding to checkout with ${cart.size} items")
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController, cartItemCount)
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = if (isLoggedIn) "foodShop" else "login", // Adjust to your route logic
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("login") {
                LoginScreen(navController, onLoginSuccess = {
                    onLoginSuccess()
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                })
            }
            composable("home") {
                HomeScreen(navController)
            }
            composable("foodShop") {
                // Pass the cart management functions to FoodShopScreen
                FoodShopScreen(navController, onAddToCart)
            }
            composable("cart") {
                CartScreen(navController, onCheckout, cart)  // Pass cart information to CartScreen
            }
            // Add other screens as necessary
        }
    }
}
