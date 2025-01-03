package com.example.miniproject

sealed class Screen(val route: String) {
    data object LogIn:Screen("login")
    data object Home : Screen("home")
    data object Shops : Screen("shops")

    data object Cart : Screen("cart") {
       /* companion object {


            // Initialize the route property
            val route: String = "cart/{cartItemCount}"

            fun createRoute(cartItemCount: Int) = "cart/$cartItemCount"
        }*/
    }

    data object Profile : Screen("profile")
}