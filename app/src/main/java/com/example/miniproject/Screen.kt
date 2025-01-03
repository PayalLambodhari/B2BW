package com.example.miniproject

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Home : Screen("home")
    data object Shops : Screen("shops")

    /*data class Cart(val cartItemCount: Int) : Screen("cart/{cartItemCount}") {
        companion object {


            // Initialize the route property
            val route: String = "cart/{cartItemCount}"

            fun createRoute(cartItemCount: Int) = "cart/$cartItemCount"
        }
    }*/
    data object Cart : Screen("cart")

    data object Profile : Screen("profile")
}