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
            startDestination = if (isLoggedIn) Screen.Home.route else Screen.Login.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Login.route) {
                LoginScreen(navController, onLoginSuccess = {
                    onLoginSuccess()
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                })
            }
            composable(Screen.SignUp.route) {
                SignUpScreen(navController)
            }
            composable(Screen.Home.route) {
                HomeScreen(navController)
            }
            composable(Screen.Shops.route) {
                // Shops screen implementation
            }
            composable("foodShop") {
                FoodShopScreen(navController, onAddToCart)
            }
            composable("clothesShop") {
                ClothesShopScreen(navController, onAddToCart)
            }
            composable("vesselsShop") {
                VesselsShopScreen(navController, onAddToCart)
            }
            composable(Screen.Cart.route) {
                CartScreen(navController, onCheckout)
            }
            composable(Screen.Profile.route) {
                ProfileScreen(navController)
            }
        }
    }
}
