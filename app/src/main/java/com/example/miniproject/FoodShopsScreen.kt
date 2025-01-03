package com.example.miniproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.background // Import added here
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
    val foodItems = listOf(
        Product(
            id = 1,
            name = "Pizza",
            price = 10.0,
            description = "Delicious cheesy pizza with a variety of toppings.",
            imageUrl = "https://media.istockphoto.com/id/1442417585/photo/person-getting-a-piece-of-cheesy-pepperoni-pizza.webp?a=1&b=1&s=612x612&w=0&k=20&c=akEIJdrYnU1_iDZjvZyNdQ7CsYLuz8NTYnjCxT3UA4g=" // Pizza
        ),
        Product(
            id = 2,
            name = "Burger",
            price = 8.0,
            description = "Juicy beef burger with crispy lettuce and cheese.",
            imageUrl = "https://media.istockphoto.com/id/998309062/photo/burger-with-beef-and-cheese.webp?a=1&b=1&s=612x612&w=0&k=20&c=hthCHhBsUotyv4SCP8MdltTNv1bT6g5R9NIB1fufva0=" // Burger
        ),
        Product(
            id = 3,
            name = "Pasta",
            price = 12.0,
            description = "Creamy pasta with fresh basil and parmesan.",
            imageUrl = "https://media.istockphoto.com/id/482964545/photo/arrabiata-pasta.webp?a=1&b=1&s=612x612&w=0&k=20&c=WgBDLDed6qro4H1gamjxl5hWALBdXm6T0UGSU3d6TRo=" // Pasta
        ),
        Product(
            id = 4,
            name = "Sushi",
            price = 15.0,
            description = "Fresh sushi rolls with fish, avocado, and wasabi.",
            imageUrl = "https://media.istockphoto.com/id/1214109373/photo/elegant-japanese-dinner-with-sushi-maki.webp?a=1&b=1&s=612x612&w=0&k=20&c=NOGQIS5xeUYeniCIR1JohATI8SHzwLeI7DfEzbzc84A=" // Sushi
        ),
        Product(
            id = 5,
            name = "Salad",
            price = 7.0,
            description = "Healthy mixed greens with vinaigrette dressing.",
            imageUrl = "https://media.istockphoto.com/id/683296492/photo/roasted-sweet-potatoes-wedges-with-guacamole-and-kidney-beans.webp?a=1&b=1&s=612x612&w=0&k=20&c=ubFY9t8WXsQkzA4nzNjH7oa12CVZFSwmCeSR5YIWinU=" // Salad
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Welcome Message
        Text(
            text = "Welcome to the Food Shop!",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFFC107), // Yellow color
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )

        // Display the food items
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(foodItems) { product ->
                FoodItemCard(product = product, onAddToCart = onAddToCart, navController = navController)
            }
        }
    }
}

@Composable
fun FoodItemCard(product: Product, onAddToCart: (Product) -> Unit, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .background(Color(0xFFF1F1F1), shape = RoundedCornerShape(12.dp)) // Background applied here
            .padding(16.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(product.imageUrl),
            contentDescription = product.name,
            modifier = Modifier
                .size(250.dp)
                .padding(bottom = 16.dp)
        )

        Text(
            text = product.name,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF424242),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "$${product.price}",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFFC107), // Price in yellow color
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = product.description,
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                onAddToCart(product)
                navController.navigate("cart")
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107)), // Yellow button
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Add to Cart", fontSize = 18.sp, color = Color.White)
        }
    }
}
