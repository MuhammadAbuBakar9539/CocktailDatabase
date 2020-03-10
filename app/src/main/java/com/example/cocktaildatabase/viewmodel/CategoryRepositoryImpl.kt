package com.example.cocktaildatabase.viewmodel

import com.example.cocktaildatabase.common.network.Client
import com.example.cocktaildatabase.model.CategoryModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(private val client: Client) : CategoryRepository {
    override fun getCategoryList(): Single<CategoryModel> {
        return client.getCategoryList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}