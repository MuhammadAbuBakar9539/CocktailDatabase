package com.example.cocktaildatabase.viewmodel.repository

import com.example.cocktaildatabase.model.DrinksModel
import io.reactivex.Single

interface DrinksRepository {
    fun getDrinkList(drinkCategory:String):Single<DrinksModel>
}