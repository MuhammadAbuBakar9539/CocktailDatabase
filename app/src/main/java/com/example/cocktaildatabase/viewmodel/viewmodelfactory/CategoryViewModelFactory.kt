package com.example.cocktaildatabase.viewmodel.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cocktaildatabase.viewmodel.CategoryViewModel
import com.example.cocktaildatabase.viewmodel.repository.CategoryRepository
import javax.inject.Inject

class CategoryViewModelFactory @Inject constructor(private val repository: CategoryRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoryViewModel(
            repository
        ) as T
    }
}