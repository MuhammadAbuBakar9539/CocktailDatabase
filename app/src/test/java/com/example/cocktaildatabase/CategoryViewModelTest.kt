package com.example.cocktaildatabase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.cocktaildatabase.model.CategoryModel
import com.example.cocktaildatabase.viewmodel.CategoryViewModel
import com.example.cocktaildatabase.viewmodel.repository.CategoryRepository
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CategoryViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: CategoryRepository

    private lateinit var viewModelTest: CategoryViewModel

    @Mock
    private lateinit var categoryObserver: Observer<CategoryModel>

    @Mock
    private lateinit var categoryErrorObserver: Observer<String>

    @Mock
    private lateinit var catPbObserver: Observer<Boolean>

    private lateinit var model: CategoryModel

    @Before
    fun setup(){
        val categoryList = CategoryModel.Category("soft Drinks")
        viewModelTest = CategoryViewModel(repository)
        viewModelTest.getCategoryObservable().observeForever(categoryObserver)
        viewModelTest.getCategoryErrorObservable().observeForever(categoryErrorObserver)
        viewModelTest.progressBarObservable().observeForever(catPbObserver)
        model = CategoryModel(listOf(categoryList))
    }

    @Test
    fun `get categories call successfully`(){
        //Given
        `when`(repository.getCategoryList()).thenReturn(Single.just(model))
        //When
        viewModelTest.getCategories(true)
        //Then
        Assert.assertEquals(model, viewModelTest.getCategoryObservable().value)
        verify(categoryObserver).onChanged(model)
    }

    @Test
    fun `get categories call unSuccessful`(){
        //Given
        val error= "error"
        `when`(repository.getCategoryList()).thenReturn(Single.error(RuntimeException(error)))
        //When
        viewModelTest.getCategories(true)
        //Then
        Assert.assertEquals(error, viewModelTest.getCategoryErrorObservable().value)
        verify(categoryErrorObserver).onChanged(error)
    }

    @Test
    fun `get categoriesDb call successfully`(){
        //Given
        `when`(repository.getCategoryDbList()).thenReturn(Single.just(model))
        //When
        viewModelTest.getCategories(false)
        //Then
        Assert.assertEquals(model, viewModelTest.getCategoryObservable().value)
        verify(categoryObserver).onChanged(model)
    }

    @Test
    fun `get categoriesDb call unSuccessful`(){
        //Given
        val error= "error"
        `when`(repository.getCategoryDbList()).thenReturn(Single.error(RuntimeException(error)))
        //When
        viewModelTest.getCategories(false)
        //Then
        Assert.assertEquals(error, viewModelTest.getCategoryErrorObservable().value)
        verify(categoryErrorObserver).onChanged(error)
    }

    @Test
    fun `hide progress bar for api category list`(){
        //Given
        `when`(repository.getCategoryList()).thenReturn(Single.just(model))
        //When
        viewModelTest.getCategories(true)
        //Then

        verify(catPbObserver).onChanged(false)
    }

    @Test
    fun `hide progress bar for db category list`(){
        //Given
        `when`(repository.getCategoryDbList()).thenReturn(Single.just(model))
        //When
        viewModelTest.getCategories(false)
        //Then

        verify(catPbObserver).onChanged(false)
    }

    @Test
    fun `hide progress bar for api category list error`(){
        //Given
        val error = "error"
        `when`(repository.getCategoryList()).thenReturn(Single.error(RuntimeException(error)))
        //When
        viewModelTest.getCategories(true)
        //Then

        verify(catPbObserver).onChanged(false)
    }

    @Test
    fun `hide progress bar for db category list error`(){
        //Given
        val error = "error"
        `when`(repository.getCategoryDbList()).thenReturn(Single.error(RuntimeException(error)))
        //When
        viewModelTest.getCategories(false)
        //Then

        verify(catPbObserver).onChanged(false)
    }
}