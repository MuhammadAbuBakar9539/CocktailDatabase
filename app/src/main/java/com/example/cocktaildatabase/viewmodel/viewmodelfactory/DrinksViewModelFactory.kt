package com.example.cocktaildatabase.viewmodel.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cocktaildatabase.viewmodel.DrinksViewModel
import com.example.cocktaildatabase.viewmodel.repository.DrinksRepository
import javax.inject.Inject

class DrinksViewModelFactory@Inject constructor(private val repository: DrinksRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DrinksViewModel(repository)as T
    }
}