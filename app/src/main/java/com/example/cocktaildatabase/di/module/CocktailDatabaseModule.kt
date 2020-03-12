package com.example.cocktaildatabase.di.module

import android.app.Application
import androidx.room.Room
import com.example.cocktaildatabase.model.db.CocktailDao
import com.example.cocktaildatabase.model.db.CocktailDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CocktailDatabaseModule(private val application: Application) {
    @Provides
    @Singleton
    fun provideDatabase(): CocktailDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            CocktailDatabase::class.java,
            "Cocktail_Database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideCocktailDao(cocktailDatabase: CocktailDatabase): CocktailDao {
        return cocktailDatabase.cocktailDao()
    }
}