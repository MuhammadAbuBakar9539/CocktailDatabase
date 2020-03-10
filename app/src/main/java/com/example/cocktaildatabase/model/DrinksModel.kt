package com.example.cocktaildatabase.model


import com.google.gson.annotations.SerializedName

data class DrinksModel(
    @SerializedName("drinks") val drinks: List<Drink>
) {
    data class Drink(
        val idDrink: String,
        val strDrink: String,
        val strDrinkThumb: String
    )
}