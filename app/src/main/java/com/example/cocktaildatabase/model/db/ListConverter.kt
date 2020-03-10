package com.example.cocktaildatabase.model.db

import androidx.room.TypeConverter
import com.example.cocktaildatabase.model.CategoryModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*


class ListConverter {
    private var gson: Gson = Gson()
    @TypeConverter
    fun CategoryList(data: String?): List<CategoryModel.Category> {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type =
            object : TypeToken<List<CategoryModel.Category?>?>() {}.getType()
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<CategoryModel.Category?>?): String {
        return gson.toJson(someObjects)
    }
}