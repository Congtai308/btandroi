package ut.edu.bt_tuan04.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import ut.edu.bt_tuan04.ui.theme.screens.*
import ut.edu.bt_tuan04.viewmodel.ProductViewModel
import ut.edu.bt_tuan04.viewmodel.TaskViewModel

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val taskViewModel: TaskViewModel = viewModel()
    val productViewModel: ProductViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "taskList"
    ) {
        // Màn hình danh sách task
        composable("taskList") {
            TaskListScreen(
                viewModel = taskViewModel,
                navController = navController
            )
        }

        // Màn hình thêm task
        composable("addTask") {
            AddTaskScreen(
                viewModel = taskViewModel,
                navController = navController
            )
        }

        // Màn hình chi tiết task (truyền taskId động)
        composable(
            route = "taskDetail/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: return@composable
            TaskDetailScreen(
                taskId = taskId,
                navController = navController,
                viewModel = taskViewModel
            )
        }

        // ✅ Màn hình 3: Danh sách sản phẩm
        composable("productList") {
            ProductListScreen(
                navController = navController,
                viewModel = productViewModel
            )
        }

        // ✅ Màn hình 4: Chi tiết sản phẩm (truyền id)
        composable(
            route = "productDetail/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: -1
            ProductDetailScreen(
                productId = productId,
                viewModel = productViewModel
            )
        }
    }
}
