package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val category: String, // "Vegetables", "Fruits", "Leafy Greens"
    val description: String,
    val packingDetails: String,
    val isAvailable: Boolean = true,
    val imageType: String = "default", // "veg", "fruit", "greens", "logistics"
    val variety: String = "",
    val minOrder: String = "500 Kg",
    val sortOrder: Int = 0
)

enum class ProductCategory(val displayName: String, val iconLabel: String) {
    VEGETABLES("Vegetables", "🥬"),
    FRUITS("Fruits", "🥭"),
    LEAFY_GREENS("Leafy Greens", "🌿")
}
