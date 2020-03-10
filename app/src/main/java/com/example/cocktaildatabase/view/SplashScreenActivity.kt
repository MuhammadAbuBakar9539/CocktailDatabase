package com.example.cocktaildatabase.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.cocktaildatabase.R

class SplashScreenActivity : AppCompatActivity() {

    companion object{
        const val SPLASH_TIME_OUT:Long = 3000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()
        Handler().postDelayed(
            {
                startActivity(
                    Intent(
                        this,
                        CategoryActivity::class.java
                    )
                )
                finish()
            },
            SPLASH_TIME_OUT
        )
    }
}
