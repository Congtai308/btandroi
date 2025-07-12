package ut.edu.bt_tuan04.viewmodel

import androidx.lifecycle.ViewModel
import ut.edu.bt_tuan04.data.model.Product

class ProductViewModel : ViewModel() {

    val productList = listOf(
        Product(
            id = 1,
            name = "Nike Air Force 1",
            price = 4000000.0,
            imageUrl = "https://link1.jpg",
            description = "Giày chạy bộ nhẹ, thoải mái..."
        ),
        Product(
            id = 2,
            name = "Adidas Ultraboost",
            price = 3600000.0,
            imageUrl = "https://link2.jpg",
            description = "Siêu nhẹ, thoáng khí..."
        ),
        Product(
            id = 3,
            name = "Puma Rider",
            price = 3000000.0,
            imageUrl = "https://link3.jpg",
            description = "Phong cách retro, năng động..."
        )
    )

    fun getProductById(id: Int): Product? {
        return productList.find { it.id == id }
    }
}
