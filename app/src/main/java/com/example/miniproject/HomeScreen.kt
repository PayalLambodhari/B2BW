package com.example.miniproject

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {


    // Layout for the Home Screen
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        // Button for Food Shop
        Button(onClick = { navController.navigate("foodShop") }) {
            Text("Food Shop")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Button for Clothes Shop
        Button(onClick = { navController.navigate("clothesShop") }) {
            Text("Clothes Shop")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Button for Vessels Shop
        Button(onClick = { navController.navigate("vesselsShop") }) {
            Text("Vessels Shop")
        }
    }
}
