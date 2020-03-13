package com.example.cocktaildatabase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.cocktaildatabase.model.DrinkDetailModel
import com.example.cocktaildatabase.viewmodel.DrinkDetailViewModel
import com.example.cocktaildatabase.viewmodel.repository.DrinkDetailRepository
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DrinkDetailViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: DrinkDetailRepository

    private lateinit var viewModelTest: DrinkDetailViewModel

    @Mock
    private lateinit var drinkDetailObserver: Observer<DrinkDetailModel>

    @Mock
    private lateinit var drinkDetailErrorObserver: Observer<String>

    @Mock
    private lateinit var drinkDetailPbObserver: Observer<Boolean>

    private lateinit var model: DrinkDetailModel

    @Before
    fun setup() {
        val drink = DrinkDetailModel.Drink(
            idDrink = "123",
            strAlcoholic = "Alcoholic",
            strCategory = "soft Drink",
            strDrink = "juice",
            strDrinkThumb = "juice.png",
            strIngredient1 = "Alcohol",
            strIngredient2 = "null",
            strIngredient3 = "null",
            strIngredient4 = "null",
            strIngredient5 = "null",
            strIngredient6 = "null",
            strIngredient7 = "null",
            strIngredient8 = "null",
            strIngredient9 = "null",
            strIngredient10 = "null",
            strIngredient11 = "null",
            strIngredient12 = "null",
            strIngredient13 = "null",
            strIngredient14 = "null",
            strIngredient15 = "null",
            strInstructions = "Not good for health"
        )
        viewModelTest = DrinkDetailViewModel(repository)
        viewModelTest.getDrinkDetailObservable().observeForever(drinkDetailObserver)
        viewModelTest.getDrinkDetailErrorObservable().observeForever(drinkDetailErrorObserver)
        viewModelTest.progressBarObservable().observeForever(drinkDetailPbObserver)
        model = DrinkDetailModel(listOf(drink))
    }

    @Test
    fun `get categories call successfully`() {
        //Given
        val drinkId = "123"
        `when`(repository.getDrinkDetail(drinkId)).thenReturn(Single.just(model))
        //When
        viewModelTest.getDrinkDetail(drinkId,true)
        //Then
        Assert.assertEquals(model, viewModelTest.getDrinkDetailObservable().value)
        verify(drinkDetailObserver).onChanged(model)
    }

    @Test
    fun `get categories call unSuccessful`() {
        //Given
        val drinkId = "123"
        val error = "error"
        `when`(repository.getDrinkDetail(drinkId)).thenReturn(Single.error(RuntimeException(error)))
        //When
        viewModelTest.getDrinkDetail(drinkId,true)
        //Then
        Assert.assertEquals(error, viewModelTest.getDrinkDetailErrorObservable().value)
        verify(drinkDetailErrorObserver).onChanged(error)
    }

    @Test
    fun `get categoriesDb call successfully`() {
        //Given
        val drinkId = "123"
        `when`(repository.getDrinkDbDetail(drinkId)).thenReturn(Single.just(model))
        //When
        viewModelTest.getDrinkDetail(drinkId, false)
        //Then
        Assert.assertEquals(model, viewModelTest.getDrinkDetailObservable().value)
        verify(drinkDetailObserver).onChanged(model)
    }

    @Test
    fun `get categoriesDb call unSuccessful`() {
        //Given
        val error = "error"
        val drinkId = "123"
        `when`(repository.getDrinkDbDetail(drinkId)).thenReturn(Single.error(RuntimeException(error)))
        //When
        viewModelTest.getDrinkDetail(drinkId,false)
        //Then
        Assert.assertEquals(error, viewModelTest.getDrinkDetailErrorObservable().value)
        verify(drinkDetailErrorObserver).onChanged(error)
    }

    @Test
    fun `hide progress bar for api drink list`(){
        //Given
        val drinkId = "123"
        `when`(repository.getDrinkDetail(drinkId)).thenReturn(Single.just(model))
        //When
        viewModelTest.getDrinkDetail(drinkId, true)
        //Then

        verify(drinkDetailPbObserver).onChanged(false)
    }

    @Test
    fun `hide progress bar for db drink list`(){
        //Given
        val drinkId = "123"
        `when`(repository.getDrinkDbDetail(drinkId)).thenReturn(Single.just(model))
        //When
        viewModelTest.getDrinkDetail(drinkId, false)
        //Then

        verify(drinkDetailPbObserver).onChanged(false)
    }

    @Test
    fun `hide progress bar for api drink list error`(){
        //Given
        val error = "error"
        val drinkId = "123"
        `when`(repository.getDrinkDetail(drinkId)).thenReturn(Single.error(RuntimeException(error)))
        //When
        viewModelTest.getDrinkDetail(drinkId, true)
        //Then

        verify(drinkDetailPbObserver).onChanged(false)
    }

    @Test
    fun `hide progress bar for db drink list error`(){
        //Given
        val error = "error"
        val drinkId = "123"
        `when`(repository.getDrinkDbDetail(drinkId)).thenReturn(Single.error(RuntimeException(error)))
        //When
        viewModelTest.getDrinkDetail(drinkId, false)
        //Then

        verify(drinkDetailPbObserver).onChanged(false)
    }
}