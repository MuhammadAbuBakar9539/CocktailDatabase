package com.example.cocktaildatabase.viewmodel.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.cocktaildatabase.model.DrinkDetailModel
import io.reactivex.Completable
import io.reactivex.Single

interface DrinkDetailRepository {
    fun getDrinkDetail(drinkId: String?): Single<DrinkDetailModel>
    fun getDrinkDbDetail(drinkId: String?): Single<DrinkDetailModel>
    fun addDrinkDetail(drinkDetail: DrinkDetailModel): Completable
}