package com.example.miniproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigator()
        }
    }
}

@Composable
fun AppNavigator() {
    val navController: NavHostController = rememberNavController()
    var isLoggedIn by remember { mutableStateOf(false) }
    var cartItemCount by remember { mutableIntStateOf(0) }
    var cart by remember { mutableStateOf<List<Product>>(emptyList()) }

    val onAddToCart: (Product) -> Unit = { product ->
        cart = cart + product
        cartItemCount = cart.size
    }


    // Main App Navigation with Bottom Navigation
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController, cartItemCount)
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = if(!isLoggedIn) {Screen.LogIn.route} else {Screen.Home.route},
            modifier = androidx.compose.ui.Modifier.padding(paddingValues)
        ) {
            composable(Screen.LogIn.route) {
                LoginScreen(navController = navController, onLoginSuccess = {
                    isLoggedIn=true
                    navController.navigate(Screen.Home.route)

            })
            }
            composable(Screen.Home.route) {
                HomeScreen(navController, cartItemCount)
            }
            composable(Screen.Shops.route) {
                ShopDetailScreen(shopName = "Shop Name", onAddToCart = onAddToCart)
            }
            composable(Screen.Cart.route) {
                CartScreen(navController, cartItemCount)
                /*{
                        cart = emptyList()
                        cartItemCount = 0
                    }*/
            }
            composable(Screen.Profile.route) {
                ProfileScreen(navController)
            }
        }

    }
}





        // Show Login Screen without Bottom Navigation





