package com.example.cocktaildatabase.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktaildatabase.MyApp
import com.example.cocktaildatabase.R
import com.example.cocktaildatabase.common.BUNDLE_KEY
import com.example.cocktaildatabase.common.isConnected
import com.example.cocktaildatabase.common.replaceFragmentInsideFragment
import com.example.cocktaildatabase.di.component.DaggerCategoryComponent
import com.example.cocktaildatabase.di.module.CategoryModule
import com.example.cocktaildatabase.view.recyclerview.CategoryAdapter
import com.example.cocktaildatabase.view.recyclerview.OnCategoryRecyclerViewItemClicked
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

        viewModel.getCategories(activity?.application?.isConnected()!!)

        viewModel.getCategoryObservable().observe(viewLifecycleOwner, Observer { category ->
            rv_category_list.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            rv_category_list.adapter =
                CategoryAdapter(category, object : OnCategoryRecyclerViewItemClicked {
                    override fun onCategoryItemClicked(categoryName: String) {

                        val fragmentManager: FragmentManager? = fragmentManager

                        if (fragmentManager != null) {
                            val fragment = DrinksFragment()
                            val categoryBundle = Bundle()
                            categoryBundle.putString(BUNDLE_KEY, categoryName)
                            fragment.arguments = categoryBundle
                            replaceFragmentInsideFragment(fragmentManager, fragment, R.id.fr_drink_container)
                        }
                    }
                })

        })

        viewModel.getCategoryErrorObservable().observe(this, Observer { error ->
            lil_cat_error.visibility = View.VISIBLE
            tv_cat_errorMessage.text = error

            tv_retry.setOnClickListener {
                lil_cat_error.visibility = View.GONE
                viewModel.getCategories(activity?.application?.isConnected()!!)
            }
        })

        viewModel.progressBarObservable().observe(this, Observer { isShow->
            if (isShow){
                pb_cat_progress_bar.visibility = View.VISIBLE
            }else{
                pb_cat_progress_bar.visibility = View.GONE
            }
        })
    }
}