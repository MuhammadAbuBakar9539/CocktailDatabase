package com.example.cocktaildatabase.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.cocktaildatabase.common.network.Client
import com.example.cocktaildatabase.di.scope.ActivityScope
import com.example.cocktaildatabase.model.db.CocktailDao
import com.example.cocktaildatabase.view.fragments.DrinksFragment
import com.example.cocktaildatabase.viewmodel.CategoryViewModel
import com.example.cocktaildatabase.viewmodel.DrinksViewModel
import com.example.cocktaildatabase.viewmodel.repository.CategoryRepository
import com.example.cocktaildatabase.viewmodel.repository.CategoryRepositoryImpl
import com.example.cocktaildatabase.viewmodel.repository.DrinksRepository
import com.example.cocktaildatabase.viewmodel.repository.DrinksRepositoryImpl
import com.example.cocktaildatabase.viewmodel.viewmodelfactory.CategoryViewModelFactory
import com.example.cocktaildatabase.viewmodel.viewmodelfactory.DrinksViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class DrinksModule(private val drinksFragment: DrinksFragment) {
    @Provides
    @ActivityScope
    fun provideViewModelFactory(repository: DrinksRepository): DrinksViewModelFactory {
        return DrinksViewModelFactory(
            repository
        )
    }

    @Provides
    @ActivityScope
    fun provideViewModel(factory: DrinksViewModelFactory): DrinksViewModel {
        return ViewModelProvider(drinksFragment, factory).get(DrinksViewModel::class.java)
    }

    @Provides
    @ActivityScope
    fun provideRepository(client: Client, cocktailDao: CocktailDao): DrinksRepository {
        return DrinksRepositoryImpl(
            client, cocktailDao
        )
    }
}