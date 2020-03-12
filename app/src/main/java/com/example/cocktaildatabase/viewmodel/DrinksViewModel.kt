package com.example.cocktaildatabase.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cocktaildatabase.model.DrinksModel
import com.example.cocktaildatabase.viewmodel.repository.DrinksRepository
import io.reactivex.disposables.CompositeDisposable

class DrinksViewModel(private val repository: DrinksRepository):ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val drinksObservable = MutableLiveData<DrinksModel>()
    private val drinksErrorObservable = MutableLiveData<String>()

    fun getDrinks(drinkCategory: String?, connected:Boolean) {
        val observable = if (connected) repository.getDrinkList(drinkCategory)
        else repository.getDrinkDbList(drinkCategory)
        compositeDisposable.add(
            observable.subscribe(
                { drink -> drinksObservable.value = drink },
                { DrinksError -> drinksErrorObservable.value = DrinksError.message })
        )
    }

    fun getDrinksObservable(): LiveData<DrinksModel> {
        return drinksObservable
    }

    fun getDrinksErrorObservable(): LiveData<String> {
        return drinksErrorObservable
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}