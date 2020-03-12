package com.example.cocktaildatabase.di.component

import com.example.cocktaildatabase.MyApp
import com.example.cocktaildatabase.common.network.Client
import com.example.cocktaildatabase.di.module.CocktailDatabaseModule
import com.example.cocktaildatabase.di.module.NetworkModule
import com.example.cocktaildatabase.model.db.CocktailDao
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, CocktailDatabaseModule::class])
@Singleton
interface AppComponent {
    fun inject(myApp: MyApp)
    fun client(): Client
    fun cocktailDao():CocktailDao
}