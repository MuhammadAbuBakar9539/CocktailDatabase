package com.example.cocktaildatabase.viewmodel.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cocktaildatabase.viewmodel.DrinkDetailViewModel
import com.example.cocktaildatabase.viewmodel.repository.DrinkDetailRepository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class DrinkDetailViewModelFactory @Inject constructor(private val repository: DrinkDetailRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DrinkDetailViewModel(repository) as T
    }
}