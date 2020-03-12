package com.example.cocktaildatabase.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cocktaildatabase.model.CategoryModel
import com.example.cocktaildatabase.model.DrinkDetailModel
import com.example.cocktaildatabase.model.DrinksModel

@Database(entities = [CategoryModel::class, DrinkDetailModel::class, DrinksModel::class], version = 1)
@TypeConverters(ListConverter::class)
abstract class CocktailDatabase : RoomDatabase (){
    abstract fun cocktailDao():CocktailDao
}