package com.example.cocktaildatabase.viewmodel.repository

import android.util.Log
import com.example.cocktaildatabase.common.network.Client
import com.example.cocktaildatabase.model.CategoryModel
import com.example.cocktaildatabase.model.db.CocktailDao
import com.example.cocktaildatabase.viewmodel.repository.CategoryRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(private val client: Client, private val cocktailDao: CocktailDao) :
    CategoryRepository {
    override fun getCategoryList(): Single<CategoryModel> {
        return client.getCategoryList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { categoryList->addCategoryList(categoryList) }
    }

    override fun getCategoryDbList(): Single<CategoryModel> {
        return cocktailDao.getCategoryDbList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun addCategoryList(categoryList: CategoryModel): Completable {
        return cocktailDao.addCategoryList(categoryList).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}