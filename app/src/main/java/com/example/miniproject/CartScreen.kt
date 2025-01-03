package com.example.miniproject

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun CartScreen(
    navController: NavHostController,
    onCheckout: () -> Unit,
    cart: List<Product>  // Receive the cart list
) {
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

        if (cart.isNotEmpty()) {
            Text("Items in Cart: ${cart.size}")
            Spacer(modifier = Modifier.height(8.dp))

            // Display cart items
            cart.forEach { product ->
                Text("${product.name} - $${product.price}")
            }
        } else {
            Text("Your cart is empty")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                onCheckout()
                navController.popBackStack()  // Navigate back after checkout
            },
            enabled = cart.isNotEmpty()  // Enable if cart has items
        ) {
            Text("Checkout")
        }
    }
}
