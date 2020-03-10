package com.example.cocktaildatabase.viewmodel.repository

import com.example.cocktaildatabase.model.CategoryModel
import io.reactivex.Completable
import io.reactivex.Single

interface CategoryRepository {
    fun getCategoryList(): Single<CategoryModel>
    /*fun getCategoryDbList(): Single<CategoryModel>
    fun addCategoryList(categoryList: CategoryModel): Completable*/
}