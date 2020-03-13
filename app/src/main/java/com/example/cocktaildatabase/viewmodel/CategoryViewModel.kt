package com.example.cocktaildatabase.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cocktaildatabase.model.CategoryModel
import com.example.cocktaildatabase.viewmodel.repository.CategoryRepository
import io.reactivex.disposables.CompositeDisposable

class CategoryViewModel(private val repository: CategoryRepository) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val categoryObservable = MutableLiveData<CategoryModel>()
    private val categoryErrorObservable = MutableLiveData<String>()
    private val catPbObservable = MutableLiveData<Boolean>()

    fun progressBarObservable():LiveData<Boolean>{
        return catPbObservable
    }

    fun getCategories(connected:Boolean) {
        val observable = if (connected) repository.getCategoryList() else repository.getCategoryDbList()
        catPbObservable.value = true
        compositeDisposable.add(
            observable.subscribe(
                { category ->
                    catPbObservable.value = false
                    categoryObservable.value = category },
                { categoryError ->
                    catPbObservable.value = false
                    categoryErrorObservable.value = categoryError.message })
        )
    }

    fun getCategoryObservable(): LiveData<CategoryModel> {
        return categoryObservable
    }

    fun getCategoryErrorObservable(): LiveData<String> {
        return categoryErrorObservable
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}