package com.example.cocktaildatabase.common.network

import com.example.cocktaildatabase.common.CATEGORY_ENDPOINT
import com.example.cocktaildatabase.common.DRINKS_ENDPOINT
import com.example.cocktaildatabase.model.CategoryModel
import com.example.cocktaildatabase.model.DrinksModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Client {
    @GET(CATEGORY_ENDPOINT)
    fun getCategoryList(@Query("c") list: String = "list"): Single<CategoryModel>

    @GET(DRINKS_ENDPOINT)
    fun getDrinkList(@Query("c") categoryName: String): Single<DrinksModel>

}