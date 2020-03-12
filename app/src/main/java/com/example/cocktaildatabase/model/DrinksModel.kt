package com.example.cocktaildatabase.model


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tbl_drinks")
data class DrinksModel(
    @PrimaryKey
    @SerializedName("drinks") val drinks: List<Drink>
) {
    data class Drink(
        val idDrink: String,
        val strDrink: String,
        val strDrinkThumb: String
    )
}