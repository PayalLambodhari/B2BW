package com.example.miniproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter

@Composable
fun FoodShopScreen(navController: NavHostController, onAddToCart: (Product) -> Unit) {
    // List of food products with updated image URLs
    val foodItems = listOf(
        Product(
            id = 1,
            name = "Pizza",
            price = 10.0,
            description = "Delicious cheesy pizza with a variety of toppings.",
            imageUrl = "https://images.unsplash.com/photo-1571091718767-18b5b1457add"  // Pizza Image
        ),
        Product(
            id = 2,
            name = "Burger",
            price = 8.0,
            description = "Juicy beef burger with crispy lettuce and cheese.",
            imageUrl = "https://images.unsplash.com/photo-1550547660-d9450f859349"  // Burger Image
        ),
        Product(
            id = 3,
            name = "Pasta",
            price = 12.0,
            description = "Creamy pasta with fresh basil and parmesan.",
            imageUrl = "https://images.unsplash.com/photo-1589302168068-964664d93dc0"  // Pasta Image
        ),
        Product(
            id = 4,
            name = "Sushi",
            price = 15.0,
            description = "Fresh sushi rolls with fish, avocado, and wasabi.",
            imageUrl = "https://images.unsplash.com/photo-1606787366850-de6330128bfc"  // Sushi Image
        ),
        Product(
            id = 5,
            name = "Salad",
            price = 7.0,
            description = "Healthy mixed greens with vinaigrette dressing.",
            imageUrl = "https://images.unsplash.com/photo-1512621776951-a57141f2eefd"  // Salad Image
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(foodItems) { product ->
            FoodItemCard(product = product, onAddToCart = onAddToCart, navController = navController)
        }
    }
}

@Composable
fun FoodItemCard(product: Product, onAddToCart: (Product) -> Unit, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp)
    ) {
        // Product image
        Image(
            painter = rememberAsyncImagePainter(product.imageUrl),
            contentDescription = product.name,
            modifier = Modifier
                .size(300.dp)
                .padding(bottom = 16.dp)
        )

        // Product name
        Text(
            text = product.name,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF424242),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Product price
        Text(
            text = "$${product.price}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFFC107),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Product description
        Text(
            text = product.description,
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Add to Cart button
        Button(
            onClick = {
                onAddToCart(product)  // Add product to cart
                navController.navigate("cart")  // Navigate to Cart screen
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107)),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add to Cart", fontSize = 18.sp, color = Color.White)
        }
    }
}
