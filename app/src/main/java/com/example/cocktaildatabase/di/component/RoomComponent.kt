package com.example.cocktaildatabase.di.component

import com.example.cocktaildatabase.MyApp
import com.example.cocktaildatabase.di.module.RoomModule
import com.example.cocktaildatabase.model.db.CocktailDao
import com.example.cocktaildatabase.viewmodel.CategoryRepositoryImpl
import dagger.Component
import javax.inject.Singleton

@Component(modules = [RoomModule::class], dependencies = [AppComponent::class])
@Singleton
interface RoomComponent{
    fun cocktailDao():CocktailDao
}