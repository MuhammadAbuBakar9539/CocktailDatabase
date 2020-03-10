package com.example.cocktaildatabase.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.cocktaildatabase.common.network.Client
import com.example.cocktaildatabase.di.scope.ActivityScope
import com.example.cocktaildatabase.model.db.CocktailDao
import com.example.cocktaildatabase.view.fragments.CategoryFragment
import com.example.cocktaildatabase.viewmodel.repository.CategoryRepository
import com.example.cocktaildatabase.viewmodel.repository.CategoryRepositoryImpl
import com.example.cocktaildatabase.viewmodel.CategoryViewModel
import com.example.cocktaildatabase.viewmodel.viewmodelfactory.CategoryViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class CategoryModule(private val categoryFragment: CategoryFragment) {
    @Provides
    @ActivityScope
    fun provideViewModelFactory(repository: CategoryRepository): CategoryViewModelFactory {
        return CategoryViewModelFactory(
            repository
        )
    }

    @Provides
    @ActivityScope
    fun provideViewModel(factory: CategoryViewModelFactory): CategoryViewModel {
        return ViewModelProvider(categoryFragment, factory).get(CategoryViewModel::class.java)
    }

    @Provides
    @ActivityScope
    fun provideRepository(client: Client, cocktailDao: CocktailDao): CategoryRepository {
        return CategoryRepositoryImpl(
            client,
            cocktailDao
        )
    }
}