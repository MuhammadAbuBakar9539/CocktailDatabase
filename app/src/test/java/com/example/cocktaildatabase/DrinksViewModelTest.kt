package com.example.cocktaildatabase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.cocktaildatabase.model.DrinksModel
import com.example.cocktaildatabase.viewmodel.DrinksViewModel
import com.example.cocktaildatabase.viewmodel.repository.DrinksRepository
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DrinksViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: DrinksRepository

    private lateinit var viewModelTest: DrinksViewModel

    @Mock
    private lateinit var drinksObserver: Observer<DrinksModel>

    @Mock
    private lateinit var drinksErrorObserver: Observer<String>

    @Mock
    private lateinit var drinkPbObserver: Observer<Boolean>

    private lateinit var model: DrinksModel

    @Before
    fun setup() {
        val drink = DrinksModel.Drink(
            idDrink = "123",
            strDrink = "juice",
            strDrinkThumb = "juice.png",
            drinkCat = "soft drink"
        )
        viewModelTest = DrinksViewModel(repository)
        viewModelTest.getDrinksObservable().observeForever(drinksObserver)
        viewModelTest.getDrinksErrorObservable().observeForever(drinksErrorObserver)
        viewModelTest.progressBarObservable().observeForever(drinkPbObserver)
        model = DrinksModel(listOf(drink))
    }

    @Test
    fun `get categories call successfully`() {
        //Given
        val drinkCategory = "soft drink"
        `when`(repository.getDrinkList(drinkCategory)).thenReturn(Single.just(model))
        //When
        viewModelTest.getDrinks(drinkCategory, true)
        //Then
        Assert.assertEquals(model, viewModelTest.getDrinksObservable().value)
        verify(drinksObserver).onChanged(model)
    }

    @Test
    fun `get categories call unSuccessful`() {
        //Given
        val drinkCategory = "soft drink"
        val error = "error"
        `when`(repository.getDrinkList(drinkCategory))
            .thenReturn(Single.error(RuntimeException(error)))
        //When
        viewModelTest.getDrinks(drinkCategory, true)
        //Then
        Assert.assertEquals(error, viewModelTest.getDrinksErrorObservable().value)
        verify(drinksErrorObserver).onChanged(error)
    }

    @Test
    fun `get categoriesDb call successfully`() {
        //Given
        val drinkCategory = "soft drink"
        `when`(repository.getDrinkDbList(drinkCategory)).thenReturn(Single.just(model))
        //When
        viewModelTest.getDrinks(drinkCategory, false)
        //Then
        Assert.assertEquals(model, viewModelTest.getDrinksObservable().value)
        verify(drinksObserver).onChanged(model)
    }

    @Test
    fun `get categoriesDb call unSuccessful`() {
        //Given
        val error = "error"
        val drinkCategory = "soft drink"
        `when`(repository.getDrinkDbList(drinkCategory))
            .thenReturn(Single.error(RuntimeException(error)))
        //When
        viewModelTest.getDrinks(drinkCategory, false)
        //Then
        Assert.assertEquals(error, viewModelTest.getDrinksErrorObservable().value)
        verify(drinksErrorObserver).onChanged(error)
    }

    @Test
    fun `hide progress bar for api drink list`(){
        //Given
        val drinkCategory = "soft drink"
        `when`(repository.getDrinkList(drinkCategory)).thenReturn(Single.just(model))
        //When
        viewModelTest.getDrinks(drinkCategory, true)
        //Then

        verify(drinkPbObserver).onChanged(false)
    }

    @Test
    fun `hide progress bar for db drink list`(){
        //Given
        val drinkCategory = "soft drink"
        `when`(repository.getDrinkDbList(drinkCategory)).thenReturn(Single.just(model))
        //When
        viewModelTest.getDrinks(drinkCategory, false)
        //Then

        verify(drinkPbObserver).onChanged(false)
    }

    @Test
    fun `hide progress bar for api drink list error`(){
        //Given
        val error = "error"
        val drinkCategory = "soft drink"
        `when`(repository.getDrinkList(drinkCategory)).thenReturn(Single.error(RuntimeException(error)))
        //When
        viewModelTest.getDrinks(drinkCategory, true)
        //Then

        verify(drinkPbObserver).onChanged(false)
    }

    @Test
    fun `hide progress bar for db drink list error`(){
        //Given
        val error = "error"
        val drinkCategory = "soft drink"
        `when`(repository.getDrinkDbList(drinkCategory)).thenReturn(Single.error(RuntimeException(error)))
        //When
        viewModelTest.getDrinks(drinkCategory, false)
        //Then

        verify(drinkPbObserver).onChanged(false)
    }
}