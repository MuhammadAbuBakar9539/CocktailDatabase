package com.example.cocktaildatabase.model.db

import androidx.room.TypeConverter
import com.example.cocktaildatabase.model.CategoryModel
import com.example.cocktaildatabase.model.DrinkDetailModel
import com.example.cocktaildatabase.model.DrinksModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*


class ListConverter {
    private var gson: Gson = Gson()
    @TypeConverter
    fun categoryList(data: String?): List<CategoryModel.Category> {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type =
            object : TypeToken<List<CategoryModel.Category?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun categoryListToString(someObjects: List<CategoryModel.Category?>?): String {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun drinkDetailList(data: String?): List<DrinkDetailModel.Drink> {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type =
            object : TypeToken<List<DrinkDetailModel.Drink?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun drinkDetailListToString(someObjects: List<DrinkDetailModel.Drink?>?): String {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun drinksList(data: String?): List<DrinksModel.Drink> {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type =
            object : TypeToken<List<DrinksModel.Drink?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun drinksListToString(someObjects: List<DrinksModel.Drink?>?): String {
        return gson.toJson(someObjects)
    }
}