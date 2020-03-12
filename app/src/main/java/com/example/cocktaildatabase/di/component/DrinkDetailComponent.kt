package com.example.cocktaildatabase.di.component

import com.example.cocktaildatabase.di.module.DrinkDetailModule
import com.example.cocktaildatabase.di.scope.ActivityScope
import com.example.cocktaildatabase.view.fragments.DrinkDetailFragment
import dagger.Component

@Component(modules = [DrinkDetailModule::class], dependencies = [AppComponent::class])
@ActivityScope
interface DrinkDetailComponent {
    fun inject(drinksDetailFragment: DrinkDetailFragment)
}