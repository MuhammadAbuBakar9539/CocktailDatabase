package com.example.cocktaildatabase.di.component

import com.example.cocktaildatabase.di.module.DrinksModule
import com.example.cocktaildatabase.di.scope.ActivityScope
import com.example.cocktaildatabase.view.fragments.DrinksFragment
import dagger.Component

@Component(modules = [DrinksModule::class], dependencies = [AppComponent::class])
@ActivityScope
interface DrinksComponent {
    fun inject(drinksFragment: DrinksFragment)
}