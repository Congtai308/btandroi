package ut.edu.nav_app.ui.screens.login

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

/**
 * Màn hình đăng nhập đơn giản gồm email, mật khẩu, checkbox "Remember me" và nút đăng nhập.
 */
@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("login_prefs", Context.MODE_PRIVATE)

    var email by remember { mutableStateOf(sharedPref.getString("email", "") ?: "") }
    var password by remember { mutableStateOf(sharedPref.getString("password", "") ?: "") }
    var rememberMe by remember { mutableStateOf(sharedPref.getBoolean("remember_me", false)) }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Login", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = rememberMe,
                onCheckedChange = { rememberMe = it }
            )
            Text("Remember me")
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(10.dp))
        }

        Button(
            onClick = {
                if (email.isBlank() || password.isBlank()) {
                    errorMessage = "Email and password cannot be empty"
                } else {
                    errorMessage = ""

                    if (rememberMe) {
                        sharedPref.edit().apply {
                            putString("email", email)
                            putString("password", password)
                            putBoolean("remember_me", true)
                            apply()
                        }
                    } else {
                        sharedPref.edit().clear().apply()
                    }

                    // Giả lập đăng nhập thành công
                    // navController.navigate("home")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
    }
}