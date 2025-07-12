package ut.edu.library_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import ut.edu.library_app.ui.theme.LibraryManager
import ut.edu.library_app.ui.theme.LibraryScreen
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ut.edu.library_app.ui.theme.Library_AppTheme

class MainActivity : ComponentActivity() {
    private val libraryManager = LibraryManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                LibraryScreen(libraryManager)
            }
        }
    }
}

