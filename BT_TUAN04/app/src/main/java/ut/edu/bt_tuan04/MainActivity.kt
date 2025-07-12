package ut.edu.bt_tuan04
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ut.edu.bt_tuan04.ui.theme.BT_TUAN04Theme
import ut.edu.bt_tuan04.ui.theme.navigation.MainApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BT_TUAN04Theme {
                MainApp()
            }
        }
    }
}
