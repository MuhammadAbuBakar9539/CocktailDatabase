package com.example.cocktaildatabase.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cocktaildatabase.model.CategoryModel
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CategoryDao {
    @Query("SELECT * FROM tbl_categories")
    fun getCategoryDbList(): Single<CategoryModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCategoryList(categoryList: CategoryModel): Completable
}