package com.example.cocktaildatabase.view.fragments

import android.os.Bundle
import android.util.Log
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
import com.example.cocktaildatabase.view.recyclerview.OnRecyclerViewItemClicked
import com.example.cocktaildatabase.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.category_recycler_view.*
import javax.inject.Inject

class CategoryFragment : Fragment() {
    @Inject
    lateinit var viewModel: CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.category_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DaggerCategoryComponent.builder().appComponent((activity?.application as MyApp).component())
            .categoryModule(CategoryModule(this)).build().inject(this)

        viewModel.getCategories()

        viewModel.getCategoryObservable().observe(viewLifecycleOwner, Observer { category ->
            Log.i("myapp", "inside observer")
            rv_category_list.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            rv_category_list.adapter = CategoryAdapter(category,object: OnRecyclerViewItemClicked{
                override fun onCategoryItemClicked(categoryName: String) {
                }
            })

        })

        viewModel.getCategoryErrorObservable().observe(this, Observer { error ->
            activity?.createToast(error)
        })
    }
}