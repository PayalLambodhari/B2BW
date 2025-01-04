package com.example.miniproject

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController, onLoginSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    var isValid by remember { mutableStateOf(false) }

    val goldenYellow = Color(0xFFFFD700)
    val inputFieldColor = Color.White
    val textColor = Color.Black
    val placeholderColor = Color.Gray

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(goldenYellow),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Welcome to Wholesale Shopping!!",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = textColor,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(0.9f)
                    .shadow(8.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Login to Continue",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Email Field
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email Address") },
                        placeholder = { Text("Enter your email") },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(inputFieldColor),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = placeholderColor,
                            focusedBorderColor = goldenYellow
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Password Field
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        placeholder = { Text("Enter your password") },
                        shape = RoundedCornerShape(12.dp),
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val icon = if (passwordVisible) "🙈" else "👁️"
                            Text(
                                text = icon,
                                modifier = Modifier.clickable {
                                    passwordVisible = !passwordVisible
                                }
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(inputFieldColor),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = placeholderColor,
                            focusedBorderColor = goldenYellow
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Login Button
                    Button(
                        onClick = {
                            isValid = isValidCredentials(email, password)
                            if (/*email.trim() == "user@example.com" && password.trim() == "password"*/
                            isValid) {
                                showError = false
                                onLoginSuccess()
                                navController.navigate(Screen.Home.route) {
                                    popUpTo(Screen.Home.route) { inclusive = true }
                                }
                            } else {
                                showError = true
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = goldenYellow)
                    ) {
                        Text("Login", fontSize = 18.sp, color = textColor)
                    }

                    // Error Message
                    if (showError) {
                        Text(
                            text = "Invalid credentials",
                            color = Color.Red,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    TextButton(onClick = { navController.navigate(Screen.SignUp.route) }) {
                        Text("Don't have an account? Sign up", color = Color.Black)
                    }
                }
            }
        }
    }
}
private fun isValidCredentials(email: String, password: String): Boolean {
    val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
    val passwordPattern = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")
    return emailPattern.matches(email) && passwordPattern.matches(password)
}
