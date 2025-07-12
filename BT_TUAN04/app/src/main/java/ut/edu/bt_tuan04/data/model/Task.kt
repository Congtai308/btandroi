package ut.edu.bt_tuan04.data.model
data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val color: TaskColor,
    val imageUrl: String
)

enum class TaskColor {
    Blue, Pink, Green
}