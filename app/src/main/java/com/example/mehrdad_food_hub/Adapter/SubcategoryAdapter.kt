package com.example.mehrdad_food_hub.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mehrdad_food_hub.Model.Subcategory
import com.example.mehrdad_food_hub.databinding.ListItemSubcategoryBinding

class SubcategoryAdapter(
    // لیستی از زیرمجموعه‌ها
    // Eine Liste von Unterkategorien
    private var subcategories: List<Subcategory>,
    // تابعی که هنگام کلیک بر روی هر زیرمجموعه فراخوانی می‌شود
    // Eine Funktion, die beim Klicken auf eine Unterkategorie aufgerufen wird
    private val onSubcategoryClick: (Subcategory) -> Unit
) : RecyclerView.Adapter<SubcategoryAdapter.SubcategoryViewHolder>() {

    // ViewHolder داخلی برای نگه‌داری ویوهای آیتم‌ها
    // Interner ViewHolder zur Aufbewahrung von Item-Ansichten
    inner class SubcategoryViewHolder(val binding: ListItemSubcategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    // متد برای ایجاد ویو هولدر جدید
    // Methode zum Erstellen eines neuen ViewHolders
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubcategoryViewHolder {
        // ایجاد بایندینگ از روی فایل XML
        // Erstellung des Bindings aus der XML-Datei
        val binding =
            ListItemSubcategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubcategoryViewHolder(binding)
    }

    // متد برای دریافت تعداد آیتم‌ها
    // Methode zum Abrufen der Anzahl der Elemente
    override fun getItemCount(): Int {
        return subcategories.size
    }

    // متد برای بایند کردن داده‌ها به ویوها
    // Methode zum Binden der Daten an die Ansichten
    override fun onBindViewHolder(holder: SubcategoryViewHolder, position: Int) {
        // گرفتن آیتم فعلی از لیست زیرمجموعه‌ها
        // Aktuelles Element aus der Liste der Unterkategorien abrufen
        val item = subcategories[position]
        // تنظیم نام زیرمجموعه
        // Name der Unterkategorie festlegen
        holder.binding.nameSubcategory.text = item.name
        // تنظیم توضیحات زیرمجموعه
        // Beschreibung der Unterkategorie festlegen
        holder.binding.descriptionSubcategory.text = item.productDescription
        // تنظیم قیمت زیرمجموعه
        // Preis der Unterkategorie festlegen
        holder.binding.priceSubcategory.text = String.format("%.2f €", item.price)
        // تنظیم تصویر زیرمجموعه
        // Bild der Unterkategorie festlegen
        holder.binding.imageSubcategory.setImageResource(item.image)

        // تعریف کلیک برای هر آیتم
        // Klick-Ereignis für jedes Element definieren
        holder.binding.root.setOnClickListener {
            // فراخوانی تابع onSubcategoryClick با زیرمجموعه فعلی
            // Aufruf der onSubcategoryClick-Funktion mit der aktuellen Unterkategorie
            onSubcategoryClick(item)
        }
    }
}