package com.example.cocktaildatabase.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cocktaildatabase.common.DRINKS_ENDPOINT
import com.example.cocktaildatabase.common.DRINK_DETAIL_ENDPOINT
import com.example.cocktaildatabase.model.CategoryModel
import com.example.cocktaildatabase.model.DrinkDetailModel
import com.example.cocktaildatabase.model.DrinksModel
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.GET

@Dao
interface CocktailDao {
    @Query("SELECT * FROM tbl_categories")
    fun getCategoryDbList(): Single<CategoryModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCategoryList(categoryList: CategoryModel): Completable

    @Query("SELECT * FROM tbl_drinks WHERE 'c'= :categoryName")
    fun getDrinkDbList(categoryName: String?): Single<DrinksModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDrinkList(drinkList: DrinksModel): Completable

    @Query("SELECT * FROM tbl_drink_detail WHERE 'c'= :drinkId")
    fun getDrinkDbDetail(drinkId: String?): Single<DrinkDetailModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDrinkDetail(drinkDetail: DrinkDetailModel): Completable
}