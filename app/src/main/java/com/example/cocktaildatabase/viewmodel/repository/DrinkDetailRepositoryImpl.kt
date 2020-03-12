package com.example.cocktaildatabase.viewmodel.repository

import com.example.cocktaildatabase.common.network.Client
import com.example.cocktaildatabase.model.DrinkDetailModel
import com.example.cocktaildatabase.model.db.CocktailDao
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DrinkDetailRepositoryImpl @Inject constructor(private val client: Client, private val cocktailDao: CocktailDao):DrinkDetailRepository {
    override fun getDrinkDetail(drinkId: String?): Single<DrinkDetailModel> {
        return client.getDrinkDetail(drinkId).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { drinkDetail->addDrinkDetail(drinkDetail) }
    }

    override fun getDrinkDbDetail(drinkId: String?): Single<DrinkDetailModel> {
        return cocktailDao.getDrinkDbDetail(drinkId).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun addDrinkDetail(drinkDetail: DrinkDetailModel): Completable {
        return cocktailDao.addDrinkDetail(drinkDetail).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}