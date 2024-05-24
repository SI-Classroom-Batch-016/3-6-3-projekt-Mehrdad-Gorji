package com.example.mehrdad_food_hub.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mehrdad_food_hub.Datasource.Datasource
import com.example.mehrdad_food_hub.Model.Category
import com.example.mehrdad_food_hub.Model.Subcategory
import com.example.mehrdad_food_hub.Model.Warenkorb
import com.example.mehrdad_food_hub.R

class SharedViewModel: ViewModel() {
    // ****************************************************************************

    // زیرمجموعه‌ها
    private val _subcategories = MutableLiveData<List<Subcategory>>()  // تعریف یک MutableLiveData برای لیستی از زیرمجموعه‌ها
    val subcategories: LiveData<List<Subcategory>> get() = _subcategories  // تعریف یک LiveData عمومی برای دسترسی فقط خواندنی به زیرمجموعه‌ها

    private val _selectedSubcategory = MutableLiveData<Subcategory>()  // تعریف یک MutableLiveData برای زیرمجموعه انتخاب شده
    val selectedSubcategory: LiveData<Subcategory> get() = _selectedSubcategory  // تعریف یک LiveData عمومی برای دسترسی فقط خواندنی به زیرمجموعه انتخاب شده

    private val _currentSubcategoryIndex = MutableLiveData<Int>()  // تعریف یک MutableLiveData برای نگه داشتن شاخص زیرمجموعه فعلی

    // سبد خرید
    private val _cart = MutableLiveData<List<Warenkorb>>()  // تعریف یک MutableLiveData برای لیستی از سبد خرید
    val cart: LiveData<List<Warenkorb>> get() = _cart  // تعریف یک LiveData عمومی برای دسترسی فقط خواندنی به سبد خرید

    private val cartItems: MutableList<Warenkorb> = mutableListOf()  // تعریف یک لیست Mutable برای نگه داشتن اقلام سبد خرید

    private val _itemCount = MutableLiveData<Int>()  // تعریف یک MutableLiveData برای نگه داشتن تعداد اقلام
    val itemCount: LiveData<Int> get() = _itemCount  // تعریف یک LiveData عمومی برای دسترسی فقط خواندنی به تعداد اقلام

    // دسته‌بندی‌ها
    private val _categories = MutableLiveData<List<Category>>()  // تعریف یک MutableLiveData برای لیستی از دسته‌بندی‌ها
    val categories: LiveData<List<Category>> get() = _categories  // تعریف یک LiveData عمومی برای دسترسی فقط خواندنی به دسته‌بندی‌ها

    // مقداردهی اولیه
    init {
        _itemCount.value = cartItems.sumOf { it.productQuantity }  // مقداردهی اولیه تعداد اقلام بر اساس جمع مقادیر اقلام سبد خرید
        _currentSubcategoryIndex.value = 0  // مقداردهی اولیه شاخص زیرمجموعه فعلی به 0
        _categories.value = Datasource().categories().sortedBy { it.name }  // مقداردهی اولیه دسته‌بندی‌ها و مرتب‌سازی آن‌ها بر اساس نام
    }

    // توابع زیرمجموعه‌ها
    fun setSubcategories(subcategories: List<Subcategory>) {
        _subcategories.value = subcategories.sortedBy { it.name }  // تنظیم و مرتب‌سازی زیرمجموعه‌ها بر اساس نام
        _currentSubcategoryIndex.value = 0  // بازنشانی شاخص زیرمجموعه فعلی به 0
    }

    fun setSelectedSubcategory(subcategory: Subcategory) {
        _selectedSubcategory.value = subcategory  // تنظیم زیرمجموعه انتخاب شده
    }

    fun showNextSubcategory() {
        val currentIndex = _currentSubcategoryIndex.value ?: 0  // گرفتن شاخص فعلی یا مقدار پیش‌فرض 0
        val subcategories = _subcategories.value
        if (!subcategories.isNullOrEmpty()) {  // بررسی اینکه زیرمجموعه‌ها تهی نیستند
            val nextIndex = (currentIndex + 1) % subcategories.size  // محاسبه شاخص بعدی با توجه به اندازه زیرمجموعه‌ها
            _currentSubcategoryIndex.value = nextIndex  // تنظیم شاخص بعدی
            _selectedSubcategory.value = subcategories[nextIndex]  // تنظیم زیرمجموعه انتخاب شده بعدی
        }
    }

    fun showBackSubcategory() {
        val currentIndex = _currentSubcategoryIndex.value ?: 0  // گرفتن شاخص فعلی یا مقدار پیش‌فرض 0
        val subcategories = _subcategories.value
        if (!subcategories.isNullOrEmpty()) {  // بررسی اینکه زیرمجموعه‌ها تهی نیستند
            val backIndex = if (currentIndex - 1 < 0) subcategories.size - 1 else (currentIndex - 1)  // محاسبه شاخص قبلی یا تنظیم به آخرین شاخص در صورت منفی بودن
            _currentSubcategoryIndex.value = backIndex  // تنظیم شاخص قبلی
            _selectedSubcategory.value = subcategories[backIndex]  // تنظیم زیرمجموعه انتخاب شده قبلی
        }
    }

    // توابع سبد خرید
    fun addToCart(warenkorb: Warenkorb) {
        val existingItem = cartItems.find { it.productName == warenkorb.productName }  // جستجوی آیتم موجود با همان نام محصول
        if (existingItem != null) {
            existingItem.productQuantity += 1  // افزایش تعداد محصول در صورت موجود بودن
        } else {
            cartItems.add(warenkorb)  // افزودن آیتم جدید به سبد خرید در صورت عدم وجود
        }
        _cart.value = cartItems  // به‌روزرسانی سبد خرید
        _itemCount.value = cartItems.sumOf { it.productQuantity }  // به‌روزرسانی تعداد اقلام
    }

    fun updateQuantity(warenkorb: Warenkorb, quantity: Int) {
        val cartItem = cartItems.find { it.productName == warenkorb.productName }  // جستجوی آیتم موجود با همان نام محصول
        if (cartItem != null) {
            if (quantity > 0) {
                cartItem.productQuantity = quantity  // به‌روزرسانی تعداد محصول
            } else {
                cartItems.remove(cartItem)  // حذف آیتم از سبد خرید در صورت صفر بودن تعداد
            }
            _cart.value = cartItems  // به‌روزرسانی سبد خرید
            _itemCount.value = cartItems.sumOf { it.productQuantity }  // به‌روزرسانی تعداد اقلام
        }
    }

    fun removeItemFromCart(item: Warenkorb) {
        cartItems.remove(item)  // حذف آیتم از سبد خرید
        _cart.value = cartItems  // به‌روزرسانی سبد خرید
        _itemCount.value = cartItems.sumOf { it.productQuantity }  // به‌روزرسانی تعداد اقلام
    }

    // توابع دسته‌بندی‌ها
    fun addProductToCategory(categoryName: String, subcategory: Subcategory) {
        val currentCategories = _categories.value?.toMutableList() ?: mutableListOf()  // گرفتن لیست فعلی دسته‌بندی‌ها یا ایجاد لیست جدید در صورت تهی بودن
        val category = currentCategories.find { it.name == categoryName }  // جستجوی دسته‌بندی با نام مشخص شده
        if (category != null) {
            val updatedSubcategories = category.subcategories.toMutableList()  // گرفتن لیست زیرمجموعه‌های فعلی
            updatedSubcategories.add(subcategory)  // افزودن زیرمجموعه جدید
            val updatedCategory = category.copy(subcategories = updatedSubcategories.sortedBy { it.name })  // به‌روزرسانی و مرتب‌سازی زیرمجموعه‌ها بر اساس نام
            val index = currentCategories.indexOf(category)  // گرفتن شاخص دسته‌بندی فعلی
            currentCategories[index] = updatedCategory  // به‌روزرسانی دسته‌بندی در لیست
        } else {
            val newCategory = Category(
                name = categoryName,
                image = R.drawable.veggie_2,  // باید تصویر مناسب را اضافه کنید
                subcategories = listOf(subcategory).sortedBy { it.name }  // مرتب‌سازی زیرمجموعه‌ها بر اساس نام
            )
            currentCategories.add(newCategory)  // افزودن دسته‌بندی جدید به لیست
        }
        _categories.value = currentCategories.sortedBy { it.name }  // مرتب‌سازی دسته‌بندی‌ها بر اساس نام
    }
}

