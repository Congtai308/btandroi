package ut.edu.nav_app.ui.theme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Screen(val route: String) {
    object ForgotPassword : Screen("forgot_password")
    object VerifyCode : Screen("verify_code/{email}") {
        fun createRoute(email: String) = "verify_code/$email"
    }
    object ResetPassword : Screen("reset_password/{email}/{code}") {
        fun createRoute(email: String, code: String) = "reset_password/$email/$code"
    }
    object Confirm : Screen("confirm/{email}/{code}/{password}") {
        fun createRoute(email: String, code: String, password: String) =
            "confirm/$email/$code/$password"
    }
}
