package ut.edu.nav_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import ut.edu.nav_app.ui.screens.forgotpassword.*
import ut.edu.nav_app.ui.screens.login.LoginScreen

/**
 * Điều hướng toàn bộ các màn hình trong flow Quên mật khẩu.
 */
@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "email_screen") {

        composable("email_screen") {
            EmailScreen(navController)
        }

        composable(
            route = "verify_code_screen?email={email}",
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            VerifyCodeScreen(navController, email)
        }

        composable(
            route = "reset_password_screen?email={email}&code={code}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType },
                navArgument("code") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val code = backStackEntry.arguments?.getString("code") ?: ""
            ResetPasswordScreen(navController, email, code)
        }

        composable(
            route = "confirm_screen?email={email}&code={code}&password={password}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType },
                navArgument("code") { type = NavType.StringType },
                navArgument("password") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val code = backStackEntry.arguments?.getString("code") ?: ""
            val password = backStackEntry.arguments?.getString("password") ?: ""
            ConfirmScreen(navController, email, code, password)
        }
        composable("login_screen") {
            LoginScreen(navController)
        }
    }
}