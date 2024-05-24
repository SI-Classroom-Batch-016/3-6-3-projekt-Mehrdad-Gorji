package com.example.mehrdad_food_hub.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mehrdad_food_hub.Model.Subcategory
import com.example.mehrdad_food_hub.databinding.ListItemSubcategoryBinding

class SubcategoryAdapter (
    // لیستی از زیرمجموعه‌ها
    private var subcategories: List<Subcategory>,
    // تابعی که هنگام کلیک بر روی هر زیرمجموعه فراخوانی می‌شود
    private val onSubcategoryClick: (Subcategory) -> Unit
) : RecyclerView.Adapter<SubcategoryAdapter.SubcategoryViewHolder>() {

    // ViewHolder داخلی برای نگه‌داری ویوهای آیتم‌ها
    inner class SubcategoryViewHolder(val binding: ListItemSubcategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    // متد برای ایجاد ویو هولدر جدید
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubcategoryViewHolder {
        // ایجاد بایندینگ از روی فایل XML
        val binding = ListItemSubcategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubcategoryViewHolder(binding)
    }

    // متد برای دریافت تعداد آیتم‌ها
    override fun getItemCount(): Int {
        return subcategories.size
    }

    // متد برای بایند کردن داده‌ها به ویوها
    override fun onBindViewHolder(holder: SubcategoryViewHolder, position: Int) {
        // گرفتن آیتم فعلی از لیست زیرمجموعه‌ها
        val item = subcategories[position]
        // تنظیم نام زیرمجموعه
        holder.binding.nameSubcategory.text = item.name
        // تنظیم توضیحات زیرمجموعه
        holder.binding.descriptionSubcategory.text = item.productDescription
        // تنظیم قیمت زیرمجموعه
        holder.binding.priceSubcategory.text = String.format("%.2f €", item.price)
        // تنظیم تصویر زیرمجموعه
        holder.binding.imageSubcategory.setImageResource(item.image)

        // تعریف کلیک برای هر آیتم
        holder.binding.root.setOnClickListener {
            // فراخوانی تابع onSubcategoryClick با زیرمجموعه فعلی
            onSubcategoryClick(item)
        }
    }
}

