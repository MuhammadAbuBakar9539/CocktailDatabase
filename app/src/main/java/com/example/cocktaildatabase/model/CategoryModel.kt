package com.example.cocktaildatabase.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tbl_categories")
data class CategoryModel(
    @PrimaryKey
    @SerializedName("drinks")
    val category: List<Category>
) {
    data class Category(
        @SerializedName("strCategory")
        val strCategory: String
    )
}