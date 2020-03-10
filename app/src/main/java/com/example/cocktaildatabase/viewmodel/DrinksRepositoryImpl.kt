package com.example.cocktaildatabase.viewmodel

import com.example.cocktaildatabase.common.network.Client
import com.example.cocktaildatabase.model.DrinksModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DrinksRepositoryImpl @Inject constructor(private val client: Client):DrinksRepository {
    override fun getDrinkList(drinkCategory: String): Single<DrinksModel> {
        return client.getDrinkList(drinkCategory).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}