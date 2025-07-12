package ut.edu.library_app.ui.theme
import ut.edu.library_app.ui.theme.Book
data class Student(
    val id: Int,
    var name: String,
    val borrowedBooks: MutableList<Int> = mutableListOf()
)