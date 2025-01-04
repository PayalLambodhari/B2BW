package com.example.miniproject

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(navController: NavController, cartItemCount: Int) {
    val currentRoute = navController.currentDestination?.route
    val navItems = listOf("home","cart", "profile")

    NavigationBar {
        navItems.forEachIndexed { _, route ->
            NavigationBarItem(
                label = { Text(route.replaceFirstChar { it.uppercase() }) },
                selected = currentRoute == route,
                onClick = {
                    navController.navigate(route) {
                        // Preserve state and ensure navigating works smoothly
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    when (route) {
                        "home" -> Icon(
                            painter = painterResource(id = R.drawable.baseline_add_home_24),
                            contentDescription = "Home"
                        )
                       /* "shops" -> Icon(
                            painter = painterResource(id = R.drawable.baseline_add_card_24),
                            contentDescription = "Shops"
                        )*/
                        "cart" -> BadgedBox(badge = {
                            if (cartItemCount > 0) {
                                Badge { Text("$cartItemCount") }
                            }
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_add_shopping_cart_24),
                                contentDescription = "Cart"
                            )
                        }
                        "profile" -> Icon(
                            painter = painterResource(id = R.drawable.baseline_admin_panel_settings_24),
                            contentDescription = "Profile"
                        )
                    }
                }
            )
        }
    }
}
