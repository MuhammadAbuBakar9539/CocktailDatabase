package com.example.cocktaildatabase.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cocktaildatabase.R
import com.example.cocktaildatabase.common.addFragment
import com.example.cocktaildatabase.view.fragments.DrinkDetailFragment
import kotlinx.android.synthetic.main.activity_drink_detail.*

class DrinkDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_detail)

        title = getString(R.string.drink_detail)

        addFragment(DrinkDetailFragment(), fr_drink_detail.id )
    }
}
