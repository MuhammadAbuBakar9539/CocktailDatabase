package com.example.cocktaildatabase.viewmodel.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.cocktaildatabase.model.DrinksModel
import io.reactivex.Completable
import io.reactivex.Single

interface DrinksRepository {
    fun getDrinkList(drinkCategory: String?):Single<DrinksModel>
    fun getDrinkDbList(categoryName: String?): Single<DrinksModel>
    fun addDrinkList(drinkList: DrinksModel): Completable
}