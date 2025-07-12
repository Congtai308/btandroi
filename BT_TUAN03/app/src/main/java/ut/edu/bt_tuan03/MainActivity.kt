package ut.edu.bt_tuan03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.SpanStyle
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.ui.text.withStyle
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.Alignment
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.ui.text.style.TextDecoration
import ut.edu.bt_tuan03.ui.theme.BT_TUAN03Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BT_TUAN03Theme {
                    AppNavigation()
                }
            }
        }
    }

@Composable
fun UIComponentListScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(40.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    )
    {
        Text("UI Component List", fontSize = 20.sp, fontWeight = FontWeight.Bold)
//        Button(onClick = { navController.navigate("textDetail") })  {
//            Text("Text - Displays text")
//        }
//        Button(onClick = { navController.navigate("image") })  {
//            Text("Text - Displays image")
//        }
//        Button(onClick = { navController.navigate("textField") })  {
//            Text("Text - Input text")
//        }
//        Button(onClick = { navController.navigate("rowLayout") })  {
//            Text("Row - Horizontal layout")
//        }
        // Display Section
        SectionHeader("Display")
        ComponentButton("Text", "Displays text") {
            navController.navigate("textDetail")
        }
        ComponentButton("Image", "Displays an image") {
            navController.navigate("image")
        }

        // Input Section
        SectionHeader("Input")
        ComponentButton("TextField", "Input field for text") {
            navController.navigate("textField")
        }
        ComponentButton("PasswordField", "Input field for passwords") {
            navController.navigate("passwordField")
        }

        // Layout Section
        SectionHeader("Layout")
        ComponentButton("Column", "Arranges elements vertically") {
            navController.navigate("columnLayout")
        }
        ComponentButton("Row", "Arranges elements horizontally") {
            navController.navigate("rowLayout")
        }

        // Nút cuối màu hồng
        Button(
            onClick = { navController.navigate("textDetail")  },
            colors = ButtonDefaults.buttonColors(containerColor  = Color(0xFFFFCDD2)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text("Box", color = Color.Red, fontWeight = FontWeight.Bold)
                Text("Box", color = Color.DarkGray)
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = Color.Gray
    )
}

@Composable
fun ComponentButton(title: String, description: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor  = Color(0xFFE1F5FE)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(text = title, fontWeight = FontWeight.Bold, color = Color.Black)
            Text(text = description, color = Color.DarkGray, fontSize = 12.sp)
        }
    }
}

@Composable
fun TextDetailScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(buildAnnotatedString {
            append("The ")
            withStyle(style = SpanStyle(textDecoration = TextDecoration.LineThrough)){
                    append("quick ")
            }
            withStyle(style = SpanStyle(color = Color(0xFF8B4513),fontWeight = FontWeight.Bold)){
                append("Brown ")
            }
            append("fox ")
            withStyle(style = SpanStyle(letterSpacing = 2.sp)){
                append("jumps ")
            }
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                append("over ")
            }
            withStyle(style = SpanStyle(fontStyle = FontStyle.Italic, color = Color.Gray)){
                append("The lazy dog. ")
            }
        }, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(500.dp))// Thêm khoảng cách trước nút

        Button(onClick = {
            // Quay lại màn hình "main" (trang chủ) và xóa tất cả các màn hình ở giữa khỏi back stack.
            navController.popBackStack("main", inclusive = false)

        }) {
            Text("Back to Home")
            }

    }
}
@Composable
fun ImageScreen(){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.uth),
            contentDescription = "uth",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            contentScale = ContentScale.Crop
            )
        Text(
            text = "https://qldt.uth.edu.vn/wp-content/uploads/2023/01/logoSVIT.png",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(10.dp))
        Image(
            painter = painterResource(id = R.drawable.uth),
            contentDescription = "uth1",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            contentScale = ContentScale.Crop
            )
        Text(text = "In app", style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun TextFieldScreen() {
    var input by remember { mutableStateOf("") }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        TextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Nhap thong tin") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text("Xin chao: $input", color = Color.Red)
    }
}
@Composable
fun PasswordField() {
    var password by remember { mutableStateOf("") }
    var visible by remember { mutableStateOf(false) }
    TextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Password") },
        visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { visible = !visible }) {
                Icon(
                    imageVector = if (visible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
fun RowLayoutScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        repeat(3) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(Color(0xFF90CAF9), RoundedCornerShape(10.dp))
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
//@Composable
//fun AnotherRowExampleScreen(navController: NavController) { // Thêm NavController nếu cần nút back
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text("Another Row Example", fontSize = 20.sp, fontWeight = FontWeight.Bold)
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Ví dụ 1: Row với các item có trọng số khác nhau
//        Text("Row with weighted items:", style = MaterialTheme.typography.titleMedium)
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(50.dp)
//                .background(Color.LightGray)
//        ) {
//            Box(
//                modifier = Modifier
//                    .weight(1f) // Chiếm 1 phần
//                    .fillMaxHeight()
//                    .background(Color(0xFF81D4FA)) // Light Blue
//            ) {
//                Text("1f", Modifier.align(Alignment.Center))
//            }
//            Box(
//                modifier = Modifier
//                    .weight(2f) // Chiếm 2 phần
//                    .fillMaxHeight()
//                    .background(Color(0xFF4FC3F7)) // Blue
//            ) {
//                Text("2f", Modifier.align(Alignment.Center))
//            }
//            Box(
//                modifier = Modifier
//                    .weight(1f) // Chiếm 1 phần
//                    .fillMaxHeight()
//                    .background(Color(0xFF29B6F6)) // Darker Blue
//            ) {
//                Text("1f", Modifier.align(Alignment.Center))
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Ví dụ 2: Row với căn chỉnh dọc (verticalAlignment)
//        Text("Row with vertical alignment:", style = MaterialTheme.typography.titleMedium)
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(100.dp) // Chiều cao lớn hơn để thấy rõ căn chỉnh
//                .background(Color.LightGray),
//            horizontalArrangement = Arrangement.SpaceAround,
//            verticalAlignment = Alignment.CenterVertically // Căn giữa theo chiều dọc
//        ) {
//            Box(modifier = Modifier.size(50.dp).background(Color.Red))
//            Box(modifier = Modifier.size(30.dp).background(Color.Green)) // Kích thước khác nhau
//            Box(modifier = Modifier.size(50.dp).background(Color.Blue))
//        }
//
//        Spacer(modifier = Modifier.weight(1f)) // Đẩy nút Back xuống dưới (nếu có)
//
//        Button(onClick = { navController.popBackStack() }) { // Nút back đơn giản
//            Text("Back")
//        }
//    }
//}
@Preview(showBackground = true)
@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { UIComponentListScreen(navController) }
        composable("textDetail") { TextDetailScreen(navController) }
        composable("image") { ImageScreen() }
        composable("textField") { TextFieldScreen() }
        composable("rowLayout") { RowLayoutScreen() }
    }
}

