package com.example.miniproject

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, cartItemCount: Int) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Welcome to Home, Enjoy Shopping")
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(navController, cartItemCount)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Select a Shop",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = { navController.navigate("foodShop") }) {
                Text("Food Shop")
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { navController.navigate("clothesShop") }) {
                Text("Clothes Shop")
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { navController.navigate("vesselsShop") }) {
                Text("Vessels Shop")
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = {
                navController.navigate(Screen.Cart.route)
            }) {
                Text("Go to Cart (${cartItemCount})")
            }
        }
    }
}