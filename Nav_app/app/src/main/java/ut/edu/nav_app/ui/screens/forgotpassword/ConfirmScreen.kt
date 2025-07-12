package ut.edu.nav_app.ui.screens.forgotpassword
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

/**
 * Màn hình xác nhận sau khi đặt lại mật khẩu thành công.
 */
@Composable
fun ConfirmScreen(navController: NavController, email: String, code: String, password: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("SmartTasks", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(20.dp))
        Text("Password Reset Successfully!", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Email: $email")
        Text("Code: $code")
        Text("Password: $password")

        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            navController.navigate("login_screen") {
                popUpTo("confirm_screen") { inclusive = true }
            }
        }) {
            Text("Go to Login")
        }

    }

}

