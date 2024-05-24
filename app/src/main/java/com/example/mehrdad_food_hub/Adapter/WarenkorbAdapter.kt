package com.example.mehrdad_food_hub.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mehrdad_food_hub.Model.Category
import com.example.mehrdad_food_hub.Model.Subcategory
import com.example.mehrdad_food_hub.Model.Warenkorb
import com.example.mehrdad_food_hub.databinding.ListItemCategoryBinding
import com.example.mehrdad_food_hub.databinding.ListItemWarenkorbBinding

class WarenkorbAdapter(  // لیستی از دسته‌بندی‌ها
    // لیستی از محصولات موجود در سبد خرید
    private val warenkorb: List<Warenkorb>,
    // تابعی که هنگام تغییر تعداد محصول فراخوانی می‌شود
    private val onQuantityChange: (Warenkorb, Int) -> Unit,
    // تابعی که هنگام حذف محصول از سبد خرید فراخوانی می‌شود
    private val onItemRemove: (Warenkorb) -> Unit,
) : RecyclerView.Adapter<WarenkorbAdapter.WarenkorbViewHolder>() {

    // ViewHolder داخلی برای نگه‌داری ویوهای آیتم‌ها
    inner class WarenkorbViewHolder(val binding: ListItemWarenkorbBinding) :
        RecyclerView.ViewHolder(binding.root)

    // متد برای ایجاد ویو هولدر جدید
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WarenkorbViewHolder {
        // ایجاد بایندینگ از روی فایل XML
        val binding = ListItemWarenkorbBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WarenkorbViewHolder(binding)
    }

    // متد برای دریافت تعداد آیتم‌ها
    override fun getItemCount(): Int {
        return warenkorb.size
    }

    // متد برای بایند کردن داده‌ها به ویوها
    override fun onBindViewHolder(holder: WarenkorbViewHolder, position: Int) {
        // گرفتن آیتم فعلی از لیست محصولات سبد خرید
        val item = warenkorb[position]
        // تنظیم نام محصول
        holder.binding.productNameWarenkorb.text = item.productName
        // تنظیم توضیحات محصول
        holder.binding.productDescriptionWarenkorb.text = item.productDescription
        // تنظیم قیمت محصول
        holder.binding.productPreisWarenkorb.text = String.format("%.2f €", item.productPrice)
        // تنظیم تصویر محصول
        holder.binding.productImageWarenkorb.setImageResource(item.productImage)
        // تنظیم تعداد محصول
        holder.binding.quantityZahlWarenkorb.text = item.productQuantity.toString()
        // تنظیم قیمت کل محصول بر اساس تعداد
        holder.binding.totalPreisWarenkorb.text = String.format("%.2f €", item.productPrice * item.productQuantity)

        // افزایش تعداد محصول
        holder.binding.plusIconWarenkorb.setOnClickListener {
            val newQuantity = item.productQuantity + 1
            onQuantityChange(item, newQuantity)
            holder.binding.quantityZahlWarenkorb.text = newQuantity.toString()
            holder.binding.totalPreisWarenkorb.text = String.format("%.2f €", item.productPrice * newQuantity)
        }

        // کاهش تعداد محصول
        holder.binding.minusIconWarenkorb.setOnClickListener {
            if (item.productQuantity > 1) {
                val newQuantity = item.productQuantity - 1
                onQuantityChange(item, newQuantity)
                holder.binding.quantityZahlWarenkorb.text = newQuantity.toString()
                holder.binding.totalPreisWarenkorb.text = String.format("%.2f €", item.productPrice * newQuantity)
            } else {
                onQuantityChange(item, 1)
            }
        }

        // حذف محصول از سبد خرید
        holder.binding.deletIconWarenkorb.setOnClickListener {
            onItemRemove(item)
        }
    }
}





