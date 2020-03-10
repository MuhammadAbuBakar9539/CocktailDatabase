package com.example.cocktaildatabase.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cocktaildatabase.R
import com.example.cocktaildatabase.common.addFragment
import com.example.cocktaildatabase.view.fragments.CategoryFragment
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        title = getString(R.string.categories)

        addFragment(CategoryFragment(), fr_category_container.id)
    }
}
