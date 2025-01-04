package com.example.miniproject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun VesselsShopScreen(navController: NavController, onAddToCart: (Product) -> Unit) {
    val products = listOf(
        Product(1, "Plate", 5.0, "Ceramic dinner plate", "https://plus.unsplash.com/premium_photo-1666739031600-60d51173144a?w=500&auto=format&fit=crop&q=60"),
        Product(2, "Cup", 3.0, "Ceramic coffee cup", "https://plus.unsplash.com/premium_photo-1668972393936-31fea424d8eb?w=500&auto=format&fit=crop&q=60"),
        Product(3, "Bowl", 8.0, "Small soup bowl", "https://plus.unsplash.com/premium_photo-1714841438314-68ba9d4d5644?w=500&auto=format&fit=crop&q=60"),
        Product(4, "Teapot", 12.0, "Traditional teapot", "https://plus.unsplash.com/premium_photo-1680172799933-f1414c803dd3?w=500&auto=format&fit=crop&q=60"),
        Product(5, "Glass", 2.0, "Transparent drinking glass", "https://images.unsplash.com/photo-1514651029128-173d2e6ea851?w=500&auto=format&fit=crop&q=60")
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            "Welcome to Vessels Shop",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFFFFC107), // Yellow color
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(products.size) { index ->
                ProductCard(product = products[index], onAddToCart = onAddToCart)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Button to navigate to Cart
        Button(
            onClick = { navController.navigate("cart") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107)), // Yellow color
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(56.dp)
        ) {
            Text("Go to Cart", style = MaterialTheme.typography.titleLarge)
        }
    }
}
