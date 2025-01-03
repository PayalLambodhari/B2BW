package com.example.miniproject

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Profile Information",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Add more profile info or settings here
        Text("Name: John Doe")
        Text("Email: johndoe@example.com")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate("settingsScreen") // Navigate to a settings screen
        }) {
            Text("Settings")
        }
    }
}
