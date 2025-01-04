package com.example.miniproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
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

    val onRemoveFromCart: (Product) -> Unit = { product ->
        cart = cart.filter { it.id != product.id }
        cartItemCount = cart.size
    }

    val onCheckout: () -> Unit = {
        println("Proceeding to checkout with ${cart.size} items")
        cart = emptyList()  // Clear cart after checkout
        cartItemCount = 0
    }

    Scaffold(
      /*  bottomBar = {
            BottomNavigationBar(navController, cartItemCount)
        }*/
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = if (isLoggedIn) "home" else "login",
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
                HomeScreen(navController,cartItemCount)
            }
            composable("foodShop") {
                FoodShopScreen(navController, onAddToCart)
            }
            composable("clothesShop") {
                ClothesShopScreen(navController, onAddToCart)
            }
            composable("vessels") {
                VesselsShopScreen(navController = navController, onAddToCart = onAddToCart)
            }
            composable("signUp") {
                SignUpScreen(navController)
            }
            composable("cart") {
                CartScreen(
                    navController = navController,
                    onCheckout = onCheckout,
                    cart = cart,
                    onRemoveFromCart = onRemoveFromCart
                )
            }
            composable("profile") {
                ProfileScreen(navController, onLogout = {
                    onLoginSuccess()
                    navController.navigate("login") {
                        popUpTo("login") { inclusive = true }
                    }
                })
            }
        }
    }
}
