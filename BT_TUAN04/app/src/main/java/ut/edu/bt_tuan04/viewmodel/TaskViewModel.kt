package ut.edu.bt_tuan04.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import ut.edu.bt_tuan04.data.model.Task
import ut.edu.bt_tuan04.data.model.TaskColor

class TaskViewModel : ViewModel() {

    private val _taskList = mutableStateListOf<Task>()
    val taskList: List<Task> get() = _taskList

    private var nextId = 1

    fun addTask(title: String, description: String, color: TaskColor, imageUrl: String) {
        val task = Task(
            id = nextId++,
            title = title,
            description = description,
            color = color,
            imageUrl = imageUrl
        )
        _taskList.add(task)
    }

    fun getTaskById(id: Int): Task? {
        return _taskList.find { it.id == id }
    }
}
