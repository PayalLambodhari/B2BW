package com.example.miniproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

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
            startDestination = if (isLoggedIn) "home" else "login",  // Updated to "home" route
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
                // Food Shop Screen
                FoodShopScreen(navController, onAddToCart)
            }
            composable("clothesShop") {
                // Clothes Shop Screen
                ClothesShopScreen(navController, onAddToCart)
            }
            composable("cart") {
                // Cart Screen
                CartScreen(navController, onCheckout, cart)
            }
            composable("profile") {
                // Profile Screen
                ProfileScreen(navController)
            }
        }
    }
}

// Example ProfileScreen
@Composable
fun ProfileScreen(navController: NavHostController) {
    Column {
        Text(text = "Profile Screen")
        Button(onClick = { navController.navigate("home") }) {
            Text("Go to Home")
        }
    }
}
