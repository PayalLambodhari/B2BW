package com.example.miniproject

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController

@Composable
fun CartScreen(
    navController: NavHostController, /*onCheckout: () -> Unit*/
    cartItemCount: Int,
    param: (Any) -> Unit, ) {
    val cartItemCount = navController
        .currentBackStackEntry
        ?.arguments
        ?.getString("cartItemCount")
        ?.toIntOrNull() ?: 0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Your Cart",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (cartItemCount > 0) {
            Text("Items in Cart: $cartItemCount")
            Spacer(modifier = Modifier.height(8.dp))

            // Placeholder for cart items
            repeat(cartItemCount) {
                Text("Item ${it + 1} - $10")
            }
        } else {
            Text("Your cart is empty")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
               /* onCheckout()*/
                navController.popBackStack()
            },
            enabled = cartItemCount > 0  // Disable if cart is empty
        ) {
            Text("Checkout")
        }
    }
}
