package ut.edu.bt_tuan04.ui.theme.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ut.edu.bt_tuan04.data.model.TaskColor
import ut.edu.bt_tuan04.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    viewModel: TaskViewModel,
    navController: NavController
) {
    val tasks = viewModel.taskList

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Danh sách công việc") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("addTask")
            }) {
                Text("+")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).padding(16.dp)) {
            items(tasks) { task ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            navController.navigate("taskDetail/${task.id}")
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = when (task.color) {
                            TaskColor.Blue -> Color(0xFFD0E8FF)
                            TaskColor.Pink -> Color(0xFFFFD6E8)
                            TaskColor.Green -> Color(0xFFDFFFE0)
                        }
                    )
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(task.title, style = MaterialTheme.typography.titleMedium)
                        Text(task.description, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}
