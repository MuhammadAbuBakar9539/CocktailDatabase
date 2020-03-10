package com.example.cocktaildatabase.di.component

import com.example.cocktaildatabase.di.module.CategoryModule
import com.example.cocktaildatabase.di.scope.ActivityScope
import com.example.cocktaildatabase.view.CategoryActivity
import com.example.cocktaildatabase.view.fragments.CategoryFragment
import dagger.Component

@Component(modules = [CategoryModule::class], dependencies = [AppComponent::class])
@ActivityScope
interface CategoryComponent {
    fun inject(categoryFragment: CategoryFragment)
}