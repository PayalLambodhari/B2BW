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
fun FoodShopScreen(navController: NavHostController, onAddToCart: (Product) -> Unit) {
    // Example product list
    val products = listOf(
        Product(1, "Product 1", 10.0, "Description for product 1"),
        Product(2, "Product 2", 15.0, "Description for product 2"),
        Product(3, "Product 3", 20.0, "Description for product 3")
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Food Shop", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        // Display product list
        products.forEach { product ->
            ProductItem(product, onAddToCart)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Button to navigate to Cart
        Button(onClick = { navController.navigate("cart") }) {
            Text("Go to Cart")
        }
    }
}

