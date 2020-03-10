package com.example.cocktaildatabase.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktaildatabase.MyApp
import com.example.cocktaildatabase.R
import com.example.cocktaildatabase.common.createToast
import com.example.cocktaildatabase.di.component.DaggerCategoryComponent
import com.example.cocktaildatabase.di.module.CategoryModule
import com.example.cocktaildatabase.view.recyclerview.CategoryAdapter
import com.example.cocktaildatabase.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.category_recycler_view.*
import javax.inject.Inject

class CategoryFragment : Fragment() {
    @Inject
    lateinit var viewModel: CategoryViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View =inflater.inflate(R.layout.category_recycler_view, container, false)
        recyclerView = view.findViewById(rv_category_list.id)
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DaggerCategoryComponent.builder().appComponent((activity?.application as MyApp).component())
            .categoryModule(CategoryModule(this)).build().inject(this)

        viewModel.getCategories()

        viewModel.getCategoryObservable().observe(viewLifecycleOwner, Observer { category ->

            rv_category_list.adapter = CategoryAdapter(category)
        })

        viewModel.getCategoryErrorObservable().observe(this, Observer { error ->
            activity?.createToast(error)
        })
    }
}