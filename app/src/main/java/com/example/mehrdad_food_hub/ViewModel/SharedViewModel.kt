package com.example.mehrdad_food_hub.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mehrdad_food_hub.Datasource.Datasource
import com.example.mehrdad_food_hub.Model.Category
import com.example.mehrdad_food_hub.Model.Subcategory
import com.example.mehrdad_food_hub.Model.Warenkorb
import com.example.mehrdad_food_hub.R

class SharedViewModel : ViewModel() {
    // *********************************** زیرمجموعه‌ها // Unterkategorien ************************


    // تعریف یک MutableLiveData برای لیستی از زیرمجموعه‌ها
    // Definition einer MutableLiveData für eine Liste von Unterkategorien
    private val _subcategories = MutableLiveData<List<Subcategory>>()

    // تعریف یک LiveData عمومی برای دسترسی فقط خواندنی به زیرمجموعه‌ها
    // Definition einer öffentlichen LiveData für schreibgeschützten Zugriff auf die Unterkategorien
    val subcategories: LiveData<List<Subcategory>> get() = _subcategories

    // تعریف یک MutableLiveData برای زیرمجموعه انتخاب شده
    // Definition einer MutableLiveData für die ausgewählte Unterkategorie
    private val _selectedSubcategory = MutableLiveData<Subcategory>()

    // تعریف یک LiveData عمومی برای دسترسی فقط خواندنی به زیرمجموعه انتخاب شده
    // Definition einer öffentlichen LiveData für schreibgeschützten Zugriff auf die ausgewählte Unterkategorie
    val selectedSubcategory: LiveData<Subcategory> get() = _selectedSubcategory

    // تعریف یک MutableLiveData برای نگه داشتن شاخص زیرمجموعه فعلی
    // Definition einer MutableLiveData zur Speicherung des aktuellen Unterkategorie-Index
    private val _currentSubcategoryIndex = MutableLiveData<Int>()

    // *********************************** سبد خرید // Einkaufswagen ************************
    // تعریف یک MutableLiveData برای لیستی از سبد خرید
    // Definition einer MutableLiveData für eine Liste von Warenkörben
    private val _cart = MutableLiveData<List<Warenkorb>>()

    // تعریف یک LiveData عمومی برای دسترسی فقط خواندنی به سبد خرید
    // Definition einer öffentlichen LiveData für schreibgeschützten Zugriff auf den Einkaufswagen
    val cart: LiveData<List<Warenkorb>> get() = _cart

    // تعریف یک لیست Mutable برای نگه داشتن اقلام سبد خرید
    // Definition einer veränderbaren Liste zur Speicherung der Warenkorbartikel
    private val cartItems: MutableList<Warenkorb> = mutableListOf()

    // تعریف یک MutableLiveData برای نگه داشتن تعداد اقلام
    // Definition einer MutableLiveData zur Speicherung der Artikelanzahl
    private val _itemCount = MutableLiveData<Int>()

    // تعریف یک LiveData عمومی برای دسترسی فقط خواندنی به تعداد اقلام
    // Definition einer öffentlichen LiveData für schreibgeschützten Zugriff auf die Artikelanzahl
    val itemCount: LiveData<Int> get() = _itemCount


    // *********************************** دسته‌بندی‌ها // Kategorien ************************
    // تعریف یک MutableLiveData برای لیستی از دسته‌بندی‌ها
    // Definition einer MutableLiveData für eine Liste von Kategorien
    private val _categories = MutableLiveData<List<Category>>()

    // تعریف یک LiveData عمومی برای دسترسی فقط خواندنی به دسته‌بندی‌ها
    // Definition einer öffentlichen LiveData für schreibgeschützten Zugriff auf die Kategorien
    val categories: LiveData<List<Category>> get() = _categories

    // *********************************** مقداردهی اولیه // Initialisierung ************************
    init {
        // مقداردهی اولیه تعداد اقلام بر اساس جمع مقادیر اقلام سبد خرید
        // Initialisierung der Artikelanzahl basierend auf der Summe der Artikelmengen im Warenkorb
        _itemCount.value = cartItems.sumOf { it.productQuantity }
        // مقداردهی اولیه شاخص زیرمجموعه فعلی به 0
        // Initialisierung des aktuellen Unterkategorie-Index auf 0
        _currentSubcategoryIndex.value = 0
        // مقداردهی اولیه دسته‌بندی‌ها و مرتب‌سازی آن‌ها بر اساس نام
        // Initialisierung der Kategorien und Sortierung nach Namen
        _categories.value = Datasource().categories().sortedBy { it.name }
    }

    // *********************************** توابع زیرمجموعه‌ها // Unterkategorien-Funktionen ************************
    fun setSubcategories(subcategories: List<Subcategory>) {
        // تنظیم و مرتب‌سازی زیرمجموعه‌ها بر اساس نام
        // Setzen und Sortieren der Unterkategorien nach Namen
        _subcategories.value = subcategories.sortedBy { it.name }
        // بازنشانی شاخص زیرمجموعه فعلی به 0
        // Zurücksetzen des aktuellen Unterkategorie-Index auf 0
        _currentSubcategoryIndex.value = 0
    }

    fun setSelectedSubcategory(subcategory: Subcategory) {
        // تنظیم زیرمجموعه انتخاب شده
        // Setzen der ausgewählten Unterkategorie
        _selectedSubcategory.value = subcategory
    }

    fun showNextSubcategory() {
        // گرفتن شاخص فعلی یا مقدار پیش‌فرض 0
        // Abrufen des aktuellen Index oder Standardwert 0
        val currentIndex = _currentSubcategoryIndex.value ?: 0
        val subcategories = _subcategories.value
        // بررسی اینکه زیرمجموعه‌ها تهی نیستند
        // Überprüfen, ob die Unterkategorien nicht leer sind
        if (!subcategories.isNullOrEmpty()) {
            // محاسبه شاخص بعدی با توجه به اندازه زیرمجموعه‌ها
            // Berechnung des nächsten Index basierend auf der Größe der Unterkategorien
            val nextIndex = (currentIndex + 1) % subcategories.size
            // تنظیم شاخص بعدی
            // Setzen des nächsten Index
            _currentSubcategoryIndex.value = nextIndex
            // تنظیم زیرمجموعه انتخاب شده بعدی
            // Setzen der nächsten ausgewählten Unterkategorie
            _selectedSubcategory.value = subcategories[nextIndex]
        }
    }

    fun showBackSubcategory() {
        // گرفتن شاخص فعلی یا مقدار پیش‌فرض 0
        // Abrufen des aktuellen Index oder Standardwert 0
        val currentIndex = _currentSubcategoryIndex.value ?: 0
        val subcategories = _subcategories.value
        // بررسی اینکه زیرمجموعه‌ها تهی نیستند
        // Überprüfen, ob die Unterkategorien nicht leer sind
        if (!subcategories.isNullOrEmpty()) {
            // محاسبه شاخص قبلی یا تنظیم به آخرین شاخص در صورت منفی بودن
            // Berechnung des vorherigen Index oder Setzen auf den letzten Index bei negativem Wert
            val backIndex = if (currentIndex - 1 < 0) subcategories.size - 1 else (currentIndex - 1)
            // تنظیم شاخص قبلی
            // Setzen des vorherigen Index
            _currentSubcategoryIndex.value = backIndex
            // تنظیم زیرمجموعه انتخاب شده قبلی
            // Setzen der vorherigen ausgewählten Unterkategorie
            _selectedSubcategory.value = subcategories[backIndex]
        }
    }
    // *********************************** توابع سبد خرید // Einkaufswagen-Funktionen ************************

    fun addToCart(warenkorb: Warenkorb) {
        // جستجوی آیتم موجود با همان نام محصول
        // Suche nach vorhandenem Artikel mit demselben Produktnamen
        val existingItem = cartItems.find { it.productName == warenkorb.productName }
        if (existingItem != null) {
            // افزایش تعداد محصول در صورت موجود بودن
            // Erhöhen der Produktmenge, falls vorhanden
            existingItem.productQuantity += 1
        } else {
            // افزودن آیتم جدید به سبد خرید در صورت عدم وجود
            // Hinzufügen eines neuen Artikels zum Warenkorb, falls nicht vorhanden
            cartItems.add(warenkorb)
        }
        // به‌روزرسانی سبد خرید
        // Aktualisieren des Warenkorbs
        _cart.value = cartItems
        // به‌روزرسانی تعداد اقلام
        // Aktualisieren der Artikelanzahl
        _itemCount.value = cartItems.sumOf { it.productQuantity }
    }

    fun updateQuantity(warenkorb: Warenkorb, quantity: Int) {
        // جستجوی آیتم موجود با همان نام محصول
        // Suche nach vorhandenem Artikel mit demselben Produktnamen
        val cartItem = cartItems.find { it.productName == warenkorb.productName }
        if (cartItem != null) {
            if (quantity > 0) {
                // به‌روزرسانی تعداد محصول
                // Aktualisieren der Produktmenge
                cartItem.productQuantity = quantity
            } else {
                // حذف آیتم از سبد خرید در صورت صفر بودن تعداد
                // Entfernen des Artikels aus dem Warenkorb bei Menge null
                cartItems.remove(cartItem)
            }
            // به‌روزرسانی سبد خرید
            // Aktualisieren des Warenkorbs
            _cart.value = cartItems
            // به‌روزرسانی تعداد اقلام
            // Aktualisieren der Artikelanzahl
            _itemCount.value = cartItems.sumOf { it.productQuantity }
        }
    }

    fun removeItemFromCart(item: Warenkorb) {
// حذف آیتم از سبد خرید
        // Entfernen des Artikels aus dem Warenkorb
        cartItems.remove(item)
        // به‌روزرسانی سبد خرید
        // Aktualisieren des Warenkorbs
        _cart.value = cartItems
        // به‌روزرسانی تعداد اقلام
        // Aktualisieren der Artikelanzahl
        _itemCount.value = cartItems.sumOf { it.productQuantity }
    }

    // توابع دسته‌بندی‌ها // Kategorien-Funktionen
    fun addProductToCategory(categoryName: String, subcategory: Subcategory) {
        // گرفتن لیست فعلی دسته‌بندی‌ها یا ایجاد لیست جدید در صورت تهی بودن
        // Abrufen der aktuellen Kategorienliste oder Erstellen einer neuen Liste, falls leer
        val currentCategories = _categories.value?.toMutableList() ?: mutableListOf()
        // جستجوی دسته‌بندی با نام مشخص شده
        // Suche nach Kategorie mit angegebenem Namen
        val category = currentCategories.find { it.name == categoryName }
        if (category != null) {
            // گرفتن لیست زیرمجموعه‌های فعلی
            // Abrufen der aktuellen Unterkategorienliste
            val updatedSubcategories = category.subcategories.toMutableList()
            // افزودن زیرمجموعه جدید
            // Hinzufügen neuer Unterkategorie
            updatedSubcategories.add(subcategory)
            // به‌روزرسانی و مرتب‌سازی زیرمجموعه‌ها بر اساس نام
            // Aktualisieren und Sortieren der Unterkategorien nach Namen
            val updatedCategory =
                category.copy(subcategories = updatedSubcategories.sortedBy { it.name })
            // گرفتن شاخص دسته‌بندی فعلی
            // Abrufen des aktuellen Kategorienindex
            val index = currentCategories.indexOf(category)
            // به‌روزرسانی دسته‌بندی در لیست
            // Aktualisieren der Kategorie in der Liste
            currentCategories[index] = updatedCategory
        } else {
            val newCategory = Category(
                name = categoryName,
                image = R.drawable.veggie_2,  // باید تصویر مناسب را اضافه کنید // Ein geeignetes Bild muss hinzugefügt werden
                // مرتب‌سازی زیرمجموعه‌ها بر اساس نام
                // Sortieren der Unterkategorien nach Namen
                subcategories = listOf(subcategory).sortedBy { it.name }
            )
            // افزودن دسته‌بندی جدید به لیست
            // Hinzufügen neuer Kategorie zur Liste
            currentCategories.add(newCategory)
        }
        // مرتب‌سازی دسته‌بندی‌ها بر اساس نام
        // Sortieren der Kategorien nach Namen
        _categories.value = currentCategories.sortedBy { it.name }
    }
}
