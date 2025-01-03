package com.example.miniproject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    val goldenYellow = Color(0xFFFFD700)
    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFE57F), Color(0xFFFFC107)),
        startY = 0f,
        endY = 1000f
    )

    // Layout for the Home Screen
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Welcome Home!!",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Food Shop Button
            Button(
                onClick = { navController.navigate("foodShop") },
                colors = ButtonDefaults.buttonColors(containerColor = goldenYellow),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(60.dp)
                    .padding(vertical = 8.dp),
                elevation = ButtonDefaults.buttonElevation(8.dp)
            ) {
                Text("Food Shop", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Clothes Shop Button
            Button(
                onClick = { navController.navigate("clothesShop") },
                colors = ButtonDefaults.buttonColors(containerColor = goldenYellow),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(60.dp)
                    .padding(vertical = 8.dp),
                elevation = ButtonDefaults.buttonElevation(8.dp)
            ) {
                Text("Clothes Shop", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Vessels Shop Button
            Button(
                onClick = { navController.navigate("vesselsShop") },
                colors = ButtonDefaults.buttonColors(containerColor = goldenYellow),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(60.dp)
                    .padding(vertical = 8.dp),
                elevation = ButtonDefaults.buttonElevation(8.dp)
            ) {
                Text("Vessels Shop", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Attractive Customer Message Section
            Text(
                text = "Explore amazing deals and discounts! 🛍️",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Find your favorite items at unbeatable prices!",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "Shop now and get exclusive offers! 🎉",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}
