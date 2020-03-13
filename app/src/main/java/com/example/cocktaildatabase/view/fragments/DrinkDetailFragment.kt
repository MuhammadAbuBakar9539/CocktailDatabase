package com.example.cocktaildatabase.view.fragments

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktaildatabase.MyApp
import com.example.cocktaildatabase.R
import com.example.cocktaildatabase.common.BUNDLE_KEY
import com.example.cocktaildatabase.common.createToast
import com.example.cocktaildatabase.common.isConnected
import com.example.cocktaildatabase.common.loadImage
import com.example.cocktaildatabase.di.component.DaggerDrinkDetailComponent
import com.example.cocktaildatabase.di.module.DrinkDetailModule
import com.example.cocktaildatabase.view.recyclerview.IngredientsAdapter
import com.example.cocktaildatabase.viewmodel.DrinkDetailViewModel
import kotlinx.android.synthetic.main.drink_detail_item.*
import javax.inject.Inject

class DrinkDetailFragment : Fragment() {
    @Inject
    lateinit var viewModel: DrinkDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.drink_detail_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val drinkId = activity?.intent?.getStringExtra(BUNDLE_KEY)

        DaggerDrinkDetailComponent.builder().appComponent((activity?.application as MyApp).component())
            .drinkDetailModule(DrinkDetailModule(this)).build().inject(this)

        viewModel.getDrinkDetail(drinkId, activity?.application?.isConnected()!!)

        viewModel.getDrinkDetailObservable().observe(viewLifecycleOwner, Observer {drink->
            loadImage(drink.drinks[0].strDrinkThumb,img_drink_detail_thumb)
            tv_drink_detail_name.text = drink.drinks[0].strDrink
            tv_drink_category.text = drink.drinks[0].strCategory
            tv_drink_type_name.text = drink.drinks[0].strAlcoholic
            tv_instructions.movementMethod = ScrollingMovementMethod()
            tv_instructions.text = drink.drinks[0].strInstructions
            rv_ingredients.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            rv_ingredients.adapter = IngredientsAdapter(drink)
        })

        viewModel.getDrinkDetailErrorObservable().observe(this, Observer { error ->
            activity?.createToast(error)
        })

        viewModel.getDrinkDetailErrorObservable().observe(this, Observer { error ->
            tv_category.visibility = View.GONE
            tv_drink_type.visibility = View.GONE
            tv_ingredient_text.visibility = View.GONE
            tv_instruction.visibility = View.GONE
            lil_drink_detail_error.visibility = View.VISIBLE
            tv_drink_detail_errorMessage.text = error

            tv_retry_drink_detail.setOnClickListener {
                lil_drink_detail_error.visibility = View.GONE
                tv_category.visibility = View.VISIBLE
                tv_drink_type.visibility = View.VISIBLE
                tv_ingredient_text.visibility = View.VISIBLE
                tv_instruction.visibility = View.VISIBLE
                viewModel.getDrinkDetail(drinkId, activity?.application?.isConnected()!!)
            }
        })

        viewModel.progressBarObservable().observe(this, Observer { isShow ->
            if (isShow) {
                tv_category.visibility = View.GONE
                tv_drink_type.visibility = View.GONE
                tv_ingredient_text.visibility = View.GONE
                tv_instruction.visibility = View.GONE
                pb_drink_detail_progress_bar.visibility = View.VISIBLE
            } else {
                pb_drink_detail_progress_bar.visibility = View.GONE
                tv_category.visibility = View.VISIBLE
                tv_drink_type.visibility = View.VISIBLE
                tv_ingredient_text.visibility = View.VISIBLE
                tv_instruction.visibility = View.VISIBLE
            }
        })
    }
}