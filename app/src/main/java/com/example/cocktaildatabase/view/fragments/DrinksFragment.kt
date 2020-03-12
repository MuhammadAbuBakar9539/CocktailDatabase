package com.example.cocktaildatabase.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cocktaildatabase.MyApp
import com.example.cocktaildatabase.R
import com.example.cocktaildatabase.common.BUNDLE_KEY
import com.example.cocktaildatabase.common.createToast
import com.example.cocktaildatabase.common.isConnected
import com.example.cocktaildatabase.common.replaceFragmentInsideFragment
import com.example.cocktaildatabase.di.component.DaggerDrinksComponent
import com.example.cocktaildatabase.di.module.DrinksModule
import com.example.cocktaildatabase.view.DrinkDetailActivity
import com.example.cocktaildatabase.view.recyclerview.DrinksAdapter
import com.example.cocktaildatabase.view.recyclerview.OnDrinkRecyclerViewItemClicked
import com.example.cocktaildatabase.viewmodel.DrinksViewModel
import kotlinx.android.synthetic.main.drink_recycler_view.*
import javax.inject.Inject

class DrinksFragment : Fragment() {
    @Inject
    lateinit var viewModel: DrinksViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.drink_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        val categoryName = bundle?.getString(BUNDLE_KEY)
        arguments?.clear()

        DaggerDrinksComponent.builder().appComponent((activity?.application as MyApp).component())
            .drinksModule(DrinksModule(this)).build().inject(this)

        viewModel.getDrinks(categoryName, activity?.application?.isConnected()!!)

        viewModel.getDrinksObservable().observe(viewLifecycleOwner, Observer { drink ->
            rv_drink_list.layoutManager = GridLayoutManager(activity, 2)
            rv_drink_list.adapter = DrinksAdapter(drink, object : OnDrinkRecyclerViewItemClicked {
                override fun onDrinkItemClicked(drinkId: String) {
                    val intent = Intent(activity, DrinkDetailActivity::class.java)
                    intent.putExtra(BUNDLE_KEY, drinkId)
                    startActivity(intent)
                }
            })

        })

        viewModel.getDrinksErrorObservable().observe(this, Observer { error ->
            activity?.createToast(error)
        })
    }
}