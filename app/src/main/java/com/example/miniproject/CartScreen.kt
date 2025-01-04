package com.example.miniproject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun CartScreen(
    navController: NavHostController,
    onCheckout: () -> Unit,
    cart: List<Product>,
    onRemoveFromCart: (Product) -> Unit
) {
    var totalPrice by remember { mutableDoubleStateOf(0.0) }

    LaunchedEffect(cart) {
        totalPrice = cart.sumOf { it.price }
    }

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
            LazyColumn {
                items(cart.size) { index ->
                    CartItemRow(
                        product = cart[index],
                        onRemoveFromCart = { onRemoveFromCart(cart[index]) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Total: $${String.format("%.2f", totalPrice)}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        } else {
            Text(
                text = "Your cart is empty",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                onCheckout()
                navController.popBackStack()  // Navigate back after checkout
            },
            enabled = cart.isNotEmpty()
        ) {
            Text("Checkout")
        }
    }
}

@Composable
fun CartItemRow(
    product: Product,
    onRemoveFromCart: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${product.name} - $${String.format("%.2f", product.price)}",
            fontSize = 16.sp
        )
        IconButton(onClick = onRemoveFromCart) {
            androidx.compose.material3.Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Remove Item",
                tint = Color.Red
            )
        }
    }
}
