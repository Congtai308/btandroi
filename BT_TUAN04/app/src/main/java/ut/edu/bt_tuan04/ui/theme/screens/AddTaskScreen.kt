package ut.edu.bt_tuan04.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ut.edu.bt_tuan04.data.model.TaskColor
import ut.edu.bt_tuan04.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    viewModel: TaskViewModel,
    navController: NavController
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf(TaskColor.Blue) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Thêm công việc") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.addTask(title, description, selectedColor, imageUrl)
                navController.popBackStack()
            }) {
                Text("Add")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Tiêu đề") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Mô tả") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = imageUrl,
                onValueChange = { imageUrl = it },
                label = { Text("Image URL") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Chọn màu (radio buttons)
            Text("Chọn màu:")
            Row {
                TaskColor.values().forEach { color ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        RadioButton(
                            selected = selectedColor == color,
                            onClick = { selectedColor = color }
                        )
                        Text(text = color.name)
                    }
                }
            }
        }
    }
}
