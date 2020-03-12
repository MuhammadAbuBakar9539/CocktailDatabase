package com.example.cocktaildatabase.view.recyclerview

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktaildatabase.R
import com.example.cocktaildatabase.common.layoutInflate
import com.example.cocktaildatabase.model.DrinkDetailModel
import kotlinx.android.synthetic.main.ingredient_item.view.*

class IngredientsAdapter(private val ingredientList:DrinkDetailModel): RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder>() {

    private fun ingredientList():List<String>{
        val emptyList = ArrayList<String>()
        if(ingredientList.drinks[0].strIngredient1 != null){
            emptyList.add(ingredientList.drinks[0].strIngredient1!!)
        }
        if(ingredientList.drinks[0].strIngredient2 != null){
            emptyList.add(ingredientList.drinks[0].strIngredient2!!)
        }
        if(ingredientList.drinks[0].strIngredient3 != null){
            emptyList.add(ingredientList.drinks[0].strIngredient3!!)
        }
        if(ingredientList.drinks[0].strIngredient4 != null){
            emptyList.add(ingredientList.drinks[0].strIngredient4!!)
        }
        if(ingredientList.drinks[0].strIngredient5 != null){
            emptyList.add(ingredientList.drinks[0].strIngredient5!!)
        }
        if(ingredientList.drinks[0].strIngredient6 != null){
            emptyList.add(ingredientList.drinks[0].strIngredient6!!)
        }
        if(ingredientList.drinks[0].strIngredient7 != null){
            emptyList.add(ingredientList.drinks[0].strIngredient7!!)
        }
        if(ingredientList.drinks[0].strIngredient8 != null){
            emptyList.add(ingredientList.drinks[0].strIngredient8!!)
        }
        if(ingredientList.drinks[0].strIngredient9 != null){
            emptyList.add(ingredientList.drinks[0].strIngredient9!!)
        }
        if(ingredientList.drinks[0].strIngredient10 != null){
            emptyList.add(ingredientList.drinks[0].strIngredient10!!)
        }
        if(ingredientList.drinks[0].strIngredient11 != null){
            emptyList.add(ingredientList.drinks[0].strIngredient11!!)
        }
        if(ingredientList.drinks[0].strIngredient12 != null){
            emptyList.add(ingredientList.drinks[0].strIngredient12!!)
        }
        if(ingredientList.drinks[0].strIngredient13 != null){
            emptyList.add(ingredientList.drinks[0].strIngredient13!!)
        }
        if(ingredientList.drinks[0].strIngredient14 != null){
            emptyList.add(ingredientList.drinks[0].strIngredient14!!)
        }
        if(ingredientList.drinks[0].strIngredient15 != null){
            emptyList.add(ingredientList.drinks[0].strIngredient15!!)
        }
        return emptyList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        return IngredientViewHolder(parent.layoutInflate(R.layout.ingredient_item))
    }

    override fun getItemCount(): Int {
        return ingredientList().size
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.ingredientName.text = ingredientList()[position]
    }

    class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ingredientName:TextView = itemView.tv_ingredient_name
    }
}