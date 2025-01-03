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
// Correct imports for navigation

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

    if (isLoggedIn) {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(navController, cartItemCount)
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = androidx.compose.ui.Modifier.padding(paddingValues)
            ) {
                composable(Screen.Home.route) {
                    HomeScreen(navController, cartItemCount)
                }
                composable(Screen.Shops.route) {
                    ShopDetailScreen(shopName = "Shop Name", onAddToCart = onAddToCart)
                }
                composable(Screen.Cart.route) {
                    CartScreen(navController, cartItemCount) { updatedCartItemCount ->
                        cartItemCount = updatedCartItemCount as Int
                    }
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

fun navHost(navController: NavHostController, startDestination: String, modifier: Unit, function: () -> Unit) {

}

fun composable(route: String, function: @Composable () -> Unit) {

}
