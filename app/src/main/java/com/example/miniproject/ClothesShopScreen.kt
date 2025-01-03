package com.example.miniproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter

@Composable
fun ClothesShopScreen(navController: NavHostController, onAddToCart: (Product) -> Unit) {
    val products = listOf(
        Product(1, "T-shirt", 20.0, "Comfortable cotton T-shirt", "https://media.istockphoto.com/id/1840186962/photo/colorful-sweatshirt-collection.webp?a=1&b=1&s=612x612&w=0&k=20&c=mY3vsNfhxx8OgHceWFpbEWBerIK80Dx_4anLD3ex3Zw="),
        Product(2, "Jeans", 40.0, "Classic blue jeans", "https://plus.unsplash.com/premium_photo-1674828601362-afb73c907ebe?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8amVhbnN8ZW58MHx8MHx8fDA%3D"),
        Product(3, "Jacket", 60.0, "Stylish leather jacket", "https://media.istockphoto.com/id/1069165932/photo/black-leather-jacket-shot-from-front-and-back-isolated-on-white.webp?a=1&b=1&s=612x612&w=0&k=20&c=RrcS7vj1W-MowcFDp5g8U_8iXLlZzZ-3ZYmtLt3sfGs="),
        Product(4, "Shoes", 50.0, "Comfortable running shoes", "https://images.unsplash.com/photo-1529810313688-44ea1c2d81d3?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8c2hvZXN8ZW58MHx8MHx8fDA%3D"),
        Product(5, "Hat", 15.0, "Cool cap to protect from the sun", "https://plus.unsplash.com/premium_photo-1682535209719-839f625f8770?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8aGF0fGVufDB8fDB8fHww")
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            "Welcome to Clothes Shop",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFFFFC107), // Yellow color applied here
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

@Composable
fun ProductCard(product: Product, onAddToCart: (Product) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onAddToCart(product) }
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp), // Adding shadow
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Product Image
            Image(
                painter = rememberAsyncImagePainter(product.imageUrl),
                contentDescription = product.name,
                modifier = Modifier
                    .size(150.dp)
                    .padding(bottom = 8.dp)
            )

            // Product Name
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Product Description
            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp),
                color = Color.Gray
            )

            // Product Price
            Text(
                text = "Price: \$${product.price}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp),
                color = Color(0xFF8BC34A) // Greenish color for price
            )

            // Add to Cart Button
            Button(
                onClick = { onAddToCart(product) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107)), // Yellow color
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Add to Cart", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
