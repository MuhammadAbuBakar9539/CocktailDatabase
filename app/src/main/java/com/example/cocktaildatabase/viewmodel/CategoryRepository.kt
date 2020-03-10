package com.example.cocktaildatabase.viewmodel

import com.example.cocktaildatabase.model.CategoryModel
import io.reactivex.Single

interface CategoryRepository {
    fun getCategoryList(): Single<CategoryModel>
    /*fun getCategoryDbList(): Single<CategoryModel>
    fun addCategoryList(categoryList: CategoryModel): Completable*/
}