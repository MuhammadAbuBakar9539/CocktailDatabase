package com.example.cocktaildatabase.view.recyclerview

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktaildatabase.R
import com.example.cocktaildatabase.common.layoutInflate
import com.example.cocktaildatabase.model.CategoryModel
import kotlinx.android.synthetic.main.category_item.view.*

class CategoryAdapter(private val category: CategoryModel) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(parent.layoutInflate(R.layout.category_item))
    }

    override fun getItemCount(): Int {
        return category.drinks.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.catName.text = category.drinks[position].strCategory
    }

    class CategoryViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val catName:TextView = itemView.tv_category_name //Category Name
    }

}