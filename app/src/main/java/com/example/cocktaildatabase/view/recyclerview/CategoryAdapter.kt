package com.example.cocktaildatabase.view.recyclerview

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktaildatabase.R
import com.example.cocktaildatabase.common.layoutInflate
import com.example.cocktaildatabase.model.CategoryModel
import kotlinx.android.synthetic.main.category_item.view.*

class CategoryAdapter(
    private val categoryList: CategoryModel,
    private val onRecyclerViewItemClicked: OnCategoryRecyclerViewItemClicked
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    var rowIndex = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(parent.layoutInflate(R.layout.category_item))
    }

    override fun getItemCount(): Int {
        return categoryList.category.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        holder.categoryName.text = categoryList.category[position].strCategory
        holder.bind(categoryList.category[position].strCategory)

        if (rowIndex == position) {

            holder.clCategoryItem.setBackgroundColor(Color.parseColor("#f9cb7b"))
        }else{
            holder.clCategoryItem.setBackgroundColor(Color.parseColor("#DCFCED"))
        }
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName: TextView = itemView.tv_category_name //Category Name
        val clCategoryItem:ConstraintLayout = itemView.cl_category_item

        fun bind(categoryName: String) {
            itemView.setOnClickListener {
                rowIndex = position
                notifyDataSetChanged()
                onRecyclerViewItemClicked.onCategoryItemClicked(categoryName)
            }
        }
    }

}