package com.example.mehrdad_food_hub.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mehrdad_food_hub.Model.Category
import com.example.mehrdad_food_hub.Model.Subcategory
import com.example.mehrdad_food_hub.Model.Warenkorb
import com.example.mehrdad_food_hub.databinding.ListItemCategoryBinding
import com.example.mehrdad_food_hub.databinding.ListItemWarenkorbBinding

class WarenkorbAdapter(
    // لیستی از دسته‌بندی‌ها
    // لیستی از محصولات موجود در سبد خرید
    // Eine Liste der Produkte im Warenkorb
    private val warenkorb: List<Warenkorb>,
    // تابعی که هنگام تغییر تعداد محصول فراخوانی می‌شود
    // Eine Funktion, die beim Ändern der Produktmenge aufgerufen wird
    private val onQuantityChange: (Warenkorb, Int) -> Unit,
    // تابعی که هنگام حذف محصول از سبد خرید فراخوانی می‌شود
    // Eine Funktion, die beim Entfernen eines Produkts aus dem Warenkorb aufgerufen wird
    private val onItemRemove: (Warenkorb) -> Unit,
) : RecyclerView.Adapter<WarenkorbAdapter.WarenkorbViewHolder>() {

    // ViewHolder داخلی برای نگه‌داری ویوهای آیتم‌ها
    // Interner ViewHolder zur Aufbewahrung von Item-Ansichten
    inner class WarenkorbViewHolder(val binding: ListItemWarenkorbBinding) :
        RecyclerView.ViewHolder(binding.root)

    // متد برای ایجاد ویو هولدر جدید
    // Methode zum Erstellen eines neuen ViewHolders
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WarenkorbViewHolder {
        // ایجاد بایندینگ از روی فایل XML
        // Erstellung des Bindings aus der XML-Datei
        val binding =
            ListItemWarenkorbBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WarenkorbViewHolder(binding)
    }

    // متد برای دریافت تعداد آیتم‌ها
    // Methode zum Abrufen der Anzahl der Elemente
    override fun getItemCount(): Int {
        return warenkorb.size
    }

    // متد برای بایند کردن داده‌ها به ویوها
    // Methode zum Binden der Daten an die Ansichten
    override fun onBindViewHolder(holder: WarenkorbViewHolder, position: Int) {
        // گرفتن آیتم فعلی از لیست محصولات سبد خرید
        // Aktuelles Element aus der Liste der Warenkorb-Produkte abrufen
        val item = warenkorb[position]
        // تنظیم نام محصول
        // Produktname festlegen
        holder.binding.productNameWarenkorb.text = item.productName
        // تنظیم توضیحات محصول
        // Produktbeschreibung festlegen
        holder.binding.productDescriptionWarenkorb.text = item.productDescription
        // تنظیم قیمت محصول
        // Produktpreis festlegen
        holder.binding.productPreisWarenkorb.text = String.format("%.2f €", item.productPrice)
        // تنظیم تصویر محصول
        // Produktbild festlegen
        holder.binding.productImageWarenkorb.setImageResource(item.productImage)
        // تنظیم تعداد محصول
        // Produktmenge festlegen
        holder.binding.quantityZahlWarenkorb.text = item.productQuantity.toString()
        // تنظیم قیمت کل محصول بر اساس تعداد
        // Gesamtpreis des Produkts basierend auf der Menge festlegen
        holder.binding.totalPreisWarenkorb.text =
            String.format("%.2f €", item.productPrice * item.productQuantity)

        // افزایش تعداد محصول
        // Produktmenge erhöhen
        holder.binding.plusIconWarenkorb.setOnClickListener {
            val newQuantity = item.productQuantity + 1
            onQuantityChange(item, newQuantity)
            holder.binding.quantityZahlWarenkorb.text = newQuantity.toString()
            holder.binding.totalPreisWarenkorb.text =
                String.format("%.2f €", item.productPrice * newQuantity)
        }

        // کاهش تعداد محصول
        // Produktmenge reduzieren
        holder.binding.minusIconWarenkorb.setOnClickListener {
            if (item.productQuantity > 1) {
                val newQuantity = item.productQuantity - 1
                onQuantityChange(item, newQuantity)
                holder.binding.quantityZahlWarenkorb.text = newQuantity.toString()
                holder.binding.totalPreisWarenkorb.text =
                    String.format("%.2f €", item.productPrice * newQuantity)
            } else {
                onQuantityChange(item, 1)
            }
        }

        // حذف محصول از سبد خرید
        // Produkt aus dem Warenkorb entfernen
        holder.binding.deletIconWarenkorb.setOnClickListener {
            onItemRemove(item)
        }
    }
}


