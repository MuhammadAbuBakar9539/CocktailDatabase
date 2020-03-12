package com.example.cocktaildatabase.viewmodel.repository

import com.example.cocktaildatabase.common.network.Client
import com.example.cocktaildatabase.model.DrinksModel
import com.example.cocktaildatabase.model.db.CocktailDao
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DrinksRepositoryImpl @Inject constructor(private val client: Client, private val cocktailDao: CocktailDao):
    DrinksRepository {
    override fun getDrinkList(drinkCategory: String?): Single<DrinksModel> {
        return client.getDrinkList(drinkCategory).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { drinkList->addDrinkList(drinkList) }
    }

    override fun getDrinkDbList(categoryName: String?): Single<DrinksModel> {
        return cocktailDao.getDrinkDbList(categoryName).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun addDrinkList(drinkList: DrinksModel): Completable {
        return cocktailDao.addDrinkList(drinkList).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}