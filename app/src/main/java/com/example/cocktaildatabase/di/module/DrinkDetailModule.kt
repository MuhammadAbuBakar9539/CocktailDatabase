package com.example.cocktaildatabase.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.cocktaildatabase.common.network.Client
import com.example.cocktaildatabase.di.scope.ActivityScope
import com.example.cocktaildatabase.model.db.CocktailDao
import com.example.cocktaildatabase.view.fragments.DrinkDetailFragment
import com.example.cocktaildatabase.viewmodel.DrinkDetailViewModel
import com.example.cocktaildatabase.viewmodel.repository.DrinkDetailRepository
import com.example.cocktaildatabase.viewmodel.repository.DrinkDetailRepositoryImpl
import com.example.cocktaildatabase.viewmodel.viewmodelfactory.DrinkDetailViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class DrinkDetailModule(private val drinkDetailFragment: DrinkDetailFragment) {
    @Provides
    @ActivityScope
    fun provideViewModelFactory(repository: DrinkDetailRepository): DrinkDetailViewModelFactory {
        return DrinkDetailViewModelFactory(
            repository
        )
    }

    @Provides
    @ActivityScope
    fun provideViewModel(factory: DrinkDetailViewModelFactory): DrinkDetailViewModel {
        return ViewModelProvider(drinkDetailFragment, factory).get(DrinkDetailViewModel::class.java)
    }

    @Provides
    @ActivityScope
    fun provideRepository(client: Client, cocktailDao: CocktailDao): DrinkDetailRepository {
        return DrinkDetailRepositoryImpl(
            client, cocktailDao
        )
    }
}