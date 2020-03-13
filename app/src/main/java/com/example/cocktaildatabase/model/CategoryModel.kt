package com.example.cocktaildatabase.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tbl_categories")
data class CategoryModel(
    @PrimaryKey
    @SerializedName("drinks") val category: List<Category>
) {
    @Entity
    data class Category(
        @PrimaryKey
        @ColumnInfo(name = "catName")
        val strCategory: String
    )
}