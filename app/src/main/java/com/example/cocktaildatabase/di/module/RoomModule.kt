package com.example.cocktaildatabase.di.module

import android.content.Context
import androidx.room.Room
import com.example.cocktaildatabase.model.db.CocktailDao
import com.example.cocktaildatabase.model.db.CocktailDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): CocktailDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CocktailDatabase::class.java,
            "Cocktail_Database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideCocktailDao(cocktailDatabase: CocktailDatabase):CocktailDao{
        return cocktailDatabase.cocktailDao()
    }
}