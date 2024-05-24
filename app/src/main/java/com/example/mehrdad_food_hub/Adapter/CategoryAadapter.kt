package com.example.mehrdad_food_hub.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mehrdad_food_hub.Model.Category
import com.example.mehrdad_food_hub.Model.Subcategory
import com.example.mehrdad_food_hub.databinding.ListItemCategoryBinding

class CategoryAadapter (

    private val categories: List<Category>,
    private val onCategoryClick: (List<Subcategory>, String) -> Unit,
) : RecyclerView.Adapter<CategoryAadapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(val binding: ListItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val binding = ListItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = categories[position]
        holder.binding.TVNameCategory.text = item.name
        holder.binding.IMGCategory.setImageResource(item.image)

        holder.binding.root.setOnClickListener {
            onCategoryClick(item.subcategories, item.name)
        }
    }
}



