package com.example.miniproject

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Sample data class for Product
data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String,
    val imageUrl: String // Ensure this is added
)


@Composable
fun ShopDetailScreen(shopName: String, onAddToCart: (Product) -> Unit) {
    // Sample list of products for the shop
    val products = listOf(
        Product(
            1,
            "Product 1",
            10.0,
            "Description for product 1",
            "https://via.placeholder.com/150"
        ),
        Product(
            2,
            "Product 2",
            15.0,
            "Description for product 2",
            "https://via.placeholder.com/150"
        ),
        Product(
            3,
            "Product 3",
            20.0,
            "Description for product 3",
            "https://via.placeholder.com/150"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        // Shop name and title
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
        Text(
            text = product.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = "Price: \$${product.price}")
        Text(text = product.description)

        Spacer(modifier = Modifier.height(8.dp))

        // Button to add product to the cart
        Button(onClick = { onAddToCart(product) }) {
            Text("Add to Cart")
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
@Preview
fun ShopDetailScreenPreview() {
    // Passing a dummy function for onAddToCart
    ShopDetailScreen(shopName = "Food Shop", onAddToCart = {})
}
