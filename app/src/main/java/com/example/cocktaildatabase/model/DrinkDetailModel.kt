package com.example.cocktaildatabase.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_drink_detail")
data class DrinkDetailModel(
    @PrimaryKey
    val drinks: List<Drink>
) {
    data class Drink(
        val idDrink: String,
        val strAlcoholic: String,
        val strCategory: String,
        val strDrink: String,
        val strDrinkThumb: String,
        val strIngredient1: String?,
        val strIngredient2: String?,
        val strIngredient3: String?,
        val strIngredient4: String?,
        val strIngredient5: String?,
        val strIngredient6: String?,
        val strIngredient7: String?,
        val strIngredient8: String?,
        val strIngredient9: String?,
        val strIngredient10: String?,
        val strIngredient11: String?,
        val strIngredient12: String?,
        val strIngredient13: String?,
        val strIngredient14: String?,
        val strIngredient15: String?,
        val strInstructions: String
    )
}