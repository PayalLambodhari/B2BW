package com.example.miniproject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun VesselsShopScreen(navController: NavController, onAddToCart: (Product) -> Unit) {
    val products = listOf(
        Product(1, "Plate", 5.0, "Ceramic dinner plate", "https://via.placeholder.com/150"),
        Product(2, "Cup", 3.0, "Ceramic coffee cup", "https://via.placeholder.com/150"),
        Product(3, "Bowl", 8.0, "Small soup bowl", "https://via.placeholder.com/150"),
        Product(4, "Teapot", 12.0, "Traditional teapot", "https://via.placeholder.com/150"),
        Product(5, "Glass", 2.0, "Transparent drinking glass", "https://via.placeholder.com/150")
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            "Welcome to Vessels Shop",
            style = MaterialTheme.typography.headlineMedium,
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
        Button(onClick = { navController.navigate("cart") }) {
            Text("Go to Cart")
        }
    }
}

