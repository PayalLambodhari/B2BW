package com.example.miniproject

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object SignUp : Screen("signup")
    data object Home : Screen("home")
    data object Shops : Screen("shops")
    data object Cart : Screen("cart")
    data object Profile : Screen("profile")
}