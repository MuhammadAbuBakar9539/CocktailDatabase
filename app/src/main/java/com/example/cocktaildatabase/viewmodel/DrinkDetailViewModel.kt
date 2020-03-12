package com.example.cocktaildatabase.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cocktaildatabase.model.DrinkDetailModel
import com.example.cocktaildatabase.viewmodel.repository.DrinkDetailRepository
import io.reactivex.disposables.CompositeDisposable

class DrinkDetailViewModel(private val repository: DrinkDetailRepository) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val drinkDetailObservable = MutableLiveData<DrinkDetailModel>()
    private val drinkDetailErrorObservable = MutableLiveData<String>()

    fun getDrinkDetail(drinkId: String?, connected: Boolean) {
        val observable = if (connected) repository.getDrinkDetail(drinkId)
        else repository.getDrinkDbDetail(drinkId)
        compositeDisposable.add(
            observable.subscribe(
                { drink -> drinkDetailObservable.value = drink },
                { DrinksError -> drinkDetailErrorObservable.value = DrinksError.message })
        )
    }

    fun getDrinkDetailObservable(): LiveData<DrinkDetailModel> {
        return drinkDetailObservable
    }

    fun getDrinkDetailErrorObservable(): LiveData<String> {
        return drinkDetailErrorObservable
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}