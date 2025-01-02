package com.example.miniproject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShopDetailScreen(shopName: String, onAddToCart: (Product) -> Unit) {
    val products = listOf(
        Product(1, "Product 1", 10.0, "Description for product 1"),
        Product(2, "Product 2", 15.0, "Description for product 2"),
        Product(3, "Product 3", 20.0, "Description for product 3")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "$shopName - Product Details",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Display each product dynamically
        products.forEach { product ->
            ProductItem(product = product, onAddToCart = onAddToCart)
        }
    }
}

@Composable
fun ProductItem(product: Product, onAddToCart: (Product) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = product.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = "Price: \$${product.price}")
        Text(text = product.description)

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { onAddToCart(product) }) {
            Text("Add to Cart")
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}