package ut.edu.library_app.ui.theme
import androidx.compose.runtime.*
import ut.edu.library_app.ui.theme.Student
import ut.edu.library_app.ui.theme.Book

class LibraryManager {
    var students = mutableStateListOf<Student>()
    var books = mutableStateListOf<Book>()

    var currentStudentIndex by mutableStateOf(0)
    var selectedBookIds = mutableStateListOf<Int>()

    val currentStudent: Student
        get() = students.getOrNull(currentStudentIndex) ?: Student(0, "")

    init {
        books.addAll(
            listOf(
                Book(1, "Sách 01"),
                Book(2, "Sách 02"),
                Book(3, "Sách 03")
            )
        )

        students.addAll(
            listOf(
                Student(1, "Nguyen Van A", mutableListOf(1, 2)),
                Student(2, "Nguyen Thi B", mutableListOf(1)),
                Student(3, "Nguyen Van C")
            )
        )

        syncSelectedBooks()
    }

    fun syncSelectedBooks() {
        selectedBookIds.clear()
        selectedBookIds.addAll(currentStudent.borrowedBooks)
    }

    fun toggleBookSelection(bookId: Int) {
        if (selectedBookIds.contains(bookId)) {
            selectedBookIds.remove(bookId)
        } else {
            selectedBookIds.add(bookId)
        }
    }

    fun applyBookSelectionToStudent() {
        currentStudent.borrowedBooks.clear()
        currentStudent.borrowedBooks.addAll(selectedBookIds)
    }

    fun changeStudent() {
        currentStudentIndex = (currentStudentIndex + 1) % students.size
        syncSelectedBooks()
    }

    fun addStudent(name: String) {
        val newId = (students.maxOfOrNull { it.id } ?: 0) + 1
        students.add(Student(newId, name))
    }

    fun removeStudent(student: Student) {
        students.remove(student)
    }

    fun addBook(title: String) {
        val newId = (books.maxOfOrNull { it.id } ?: 0) + 1
        books.add(Book(newId, title))
    }

    fun removeBook(book: Book) {
        books.remove(book)
    }
}
