package com.example.cocktaildatabase.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktaildatabase.MyApp
import com.example.cocktaildatabase.R
import com.example.cocktaildatabase.common.addFragment
import com.example.cocktaildatabase.common.createToast
import com.example.cocktaildatabase.di.component.DaggerCategoryComponent
import com.example.cocktaildatabase.di.module.CategoryModule
import com.example.cocktaildatabase.view.fragments.CategoryFragment
import com.example.cocktaildatabase.view.recyclerview.CategoryAdapter
import com.example.cocktaildatabase.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.activity_category.*
import javax.inject.Inject

class CategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        title = getString(R.string.categories)

        addFragment(CategoryFragment(), fr_category_container.id)
    }
}
