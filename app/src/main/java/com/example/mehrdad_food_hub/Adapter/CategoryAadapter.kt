package com.example.mehrdad_food_hub.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mehrdad_food_hub.Model.Category
import com.example.mehrdad_food_hub.Model.Subcategory
import com.example.mehrdad_food_hub.databinding.ListItemCategoryBinding

class CategoryAadapter(

    private val categories: List<Category>, // لیست دسته‌بندی‌ها
    private val onCategoryClick: (List<Subcategory>, String) -> Unit, // تابعی که هنگام کلیک روی یک دسته‌بندی فراخوانی می‌شود.
) : RecyclerView.Adapter<CategoryAadapter.CategoryViewHolder>() {

    // این کلاس داخلی یک ViewHolder برای دسته‌بندی‌ها را تعریف می‌کند.
    // Diese innere Klasse definiert einen ViewHolder für Kategorien.
    inner class CategoryViewHolder(val binding: ListItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    // این متد برای ایجاد یک ViewHolder جدید فراخوانی می‌شود.
    // Diese Methode wird aufgerufen, um einen neuen ViewHolder zu erstellen.
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        // ایجاد ViewBinding برای آیتم دسته‌بندی.
        // Erstellung von ViewBinding für das Kategorie-Element.
        val binding =
            ListItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    // این متد تعداد آیتم‌ها را برمی‌گرداند.
    // Diese Methode gibt die Anzahl der Elemente zurück.
    override fun getItemCount(): Int {
        return categories.size
    }

    // این متد داده‌ها را به ViewHolder متصل می‌کند.
    // Diese Methode bindet die Daten an den ViewHolder.
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = categories[position]
        holder.binding.TVNameCategory.text = item.name // تنظیم نام دسته‌بندی
        holder.binding.IMGCategory.setImageResource(item.image) // تنظیم تصویر دسته‌بندی

        // تنظیم رویداد کلیک برای آیتم
        // Klick-Ereignis für das Element festlegen
        holder.binding.root.setOnClickListener {
            onCategoryClick(
                item.subcategories,
                item.name
            ) // فراخوانی تابع کلیک با زیرمجموعه‌ها و نام دسته‌بندی
        }
    }
}

