package com.example.miniproject

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController

@Composable
fun ClothesShopScreen(navController: NavHostController, onAddToCart: (Product) -> Unit) {
    val products = listOf(
        Product(1, "T-shirt", 20.0, "Comfortable cotton T-shirt", "https://via.placeholder.com/150"),
        Product(2, "Jeans", 40.0, "Classic blue jeans", "https://via.placeholder.com/150"),
        Product(3, "Jacket", 60.0, "Stylish leather jacket", "https://via.placeholder.com/150"),
        Product(4, "Shoes", 50.0, "Comfortable running shoes", "https://via.placeholder.com/150"),
        Product(
            5,
            "Hat",
            15.0,
            "Cool cap to protect from the sun",
            "https://via.placeholder.com/150"
        )
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            "Welcome to Clothes Shop",
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

@Composable
fun ProductCard(product: Product, onAddToCart: (Product) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onAddToCart(product) }
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Placeholder Box instead of Image
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(MaterialTheme.colorScheme.primary, shape = MaterialTheme.shapes.medium)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(product.name, style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(8.dp))

            Text("Price: \$${product.price}")

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = { onAddToCart(product) }) {
                Text("Add to Cart")
            }
        }
    }
}
