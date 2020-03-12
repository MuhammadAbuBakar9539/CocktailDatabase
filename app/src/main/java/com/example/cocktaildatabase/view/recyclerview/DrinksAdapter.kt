package com.example.cocktaildatabase.view.recyclerview

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktaildatabase.R
import com.example.cocktaildatabase.common.layoutInflate
import com.example.cocktaildatabase.common.loadImage
import com.example.cocktaildatabase.model.DrinksModel
import kotlinx.android.synthetic.main.drinks_item.view.*

class DrinksAdapter(private val drinkList: DrinksModel, private val onRecyclerViewItemClicked:OnDrinkRecyclerViewItemClicked) :
    RecyclerView.Adapter<DrinksAdapter.DrinkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        return DrinkViewHolder(parent.layoutInflate(R.layout.drinks_item))
    }

    override fun getItemCount(): Int {
        return drinkList.drinks.size
    }

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        holder.drinkName.text = drinkList.drinks[position].strDrink
        loadImage(drinkList.drinks[position].strDrinkThumb, holder.drinkThumbnail)
        holder.bind(drinkList.drinks[position].idDrink)
    }

    inner class DrinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val drinkThumbnail:ImageView = itemView.img_drink_thumbnail
        val drinkName:TextView = itemView.tv_drink_name
        val clDrinkItem:ConstraintLayout = itemView.cl_drink_item

        fun bind(drinkId:String){
            itemView.setOnClickListener {
                onRecyclerViewItemClicked.onDrinkItemClicked(drinkId)
            }
        }
    }
}