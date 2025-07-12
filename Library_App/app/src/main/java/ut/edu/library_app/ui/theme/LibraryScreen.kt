package ut.edu.library_app.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ut.edu.library_app.ui.theme.LibraryManager
import ut.edu.library_app.ui.theme.Book
@Composable
fun LibraryScreen(libraryManager: LibraryManager) {
    var selectedTab by remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Hệ thống Quản lý Thư viện", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))

            // Nội dung theo tab
            when (selectedTab) {
                0 -> MainTab(libraryManager)
                1 -> BookTab(libraryManager)
                2 -> StudentTab(libraryManager)
            }

            Spacer(modifier = Modifier.weight(1f)) // Đẩy nội dung lên trên
        }

        // Thanh điều hướng gắn đáy màn hình
        NavigationBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            NavigationBarItem(
                icon = { Icon(Icons.Default.Home, contentDescription = null) },
                label = { Text("Quản lý") },
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 }
            )
            NavigationBarItem(
                icon = { Icon(Icons.Default.List, contentDescription = null) },
                label = { Text("DS Sách") },
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 }
            )
            NavigationBarItem(
                icon = { Icon(Icons.Default.Person, contentDescription = null) },
                label = { Text("Sinh viên") },
                selected = selectedTab == 2,
                onClick = { selectedTab = 2 }
            )
        }
    }
}

@Composable
fun MainTab(libraryManager: LibraryManager) {
    val student = libraryManager.currentStudent

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Sinh viên: ")
        TextField(
            value = student.name,
            onValueChange = {},
            enabled = false,
            modifier = Modifier.weight(1f)
        )
        Button(onClick = { libraryManager.changeStudent() }) {
            Text("Thay đổi")
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text("Danh sách sách:")

    LazyColumn {
        items(libraryManager.books.size) { index ->
            val book = libraryManager.books[index]
            val checked = libraryManager.selectedBookIds.contains(book.id)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .background(Color(0xFFF5F5F5))
                    .padding(12.dp)
            ) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = { libraryManager.toggleBookSelection(book.id) }
                )
                Text(book.title)
            }
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Button(onClick = { libraryManager.applyBookSelectionToStudent() }) {
        Text("Thêm")
    }
}

@Composable
fun BookTab(libraryManager: LibraryManager) {
    var newTitle by remember { mutableStateOf("") }
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = newTitle,
                onValueChange = { newTitle = it },
                label = { Text("Tên sách mới") },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {
                if (newTitle.isNotBlank()) {
                    libraryManager.addBook(newTitle)
                    newTitle = ""
                }
            }) {
                Text("Thêm sách")
            }
        }

        LazyColumn {
            items(libraryManager.books.size) { index ->
                val book = libraryManager.books[index]
                Row(
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(book.title, modifier = Modifier.weight(1f))
                    Button(onClick = { libraryManager.removeBook(book) }) {
                        Text("Xóa")
                    }
                }
            }
        }
    }
}

@Composable

fun StudentTab(libraryManager: LibraryManager) {
    var newName by remember { mutableStateOf("") }
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = newName,
                onValueChange = { newName = it },
                label = { Text("Tên sinh viên mới") },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {
                if (newName.isNotBlank()) {
                    libraryManager.addStudent(newName)
                    newName = ""
                }
            }) {
                Text("Thêm sinh viên")
            }
        }

        LazyColumn {
            items(libraryManager.students.size) { index ->
                val student = libraryManager.students[index]
                val borrowedBookTitles = student.borrowedBooks
                    .mapNotNull { id -> libraryManager.books.find { it.id == id }?.title }
                    .joinToString(", ")

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .border(1.dp, Color.LightGray)
                        .padding(8.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(student.name, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                        Button(onClick = { libraryManager.removeStudent(student) }) {
                            Text("Xóa")
                        }
                    }
                    if (borrowedBookTitles.isNotBlank()) {
                        Text("Sách đã mượn: $borrowedBookTitles", fontSize = 14.sp)
                    } else {
                        Text("Chưa mượn sách nào", fontSize = 14.sp, color = Color.Gray)
                    }
                }
            }
        }
    }
}