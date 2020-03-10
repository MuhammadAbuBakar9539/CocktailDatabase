package com.example.cocktaildatabase

import android.app.Application
import com.example.cocktaildatabase.di.component.AppComponent
import com.example.cocktaildatabase.di.component.DaggerAppComponent
import com.example.cocktaildatabase.di.module.NetworkModule

class MyApp :Application(){
    override fun onCreate() {
        super.onCreate()
        component().inject(this)
    }

    fun component():AppComponent{
        return DaggerAppComponent.builder().networkModule(NetworkModule()).build()
    }
}