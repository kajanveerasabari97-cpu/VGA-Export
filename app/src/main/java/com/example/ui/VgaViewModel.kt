package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.local.InitialData
import com.example.data.model.InquiryEntity
import com.example.data.model.ProductCategory
import com.example.data.model.ProductEntity
import com.example.data.repository.VgaRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class NavTab(val title: String) {
    HOME("Home"),
    PRODUCTS("Products"),
    EXPORT_TRANSPORT("Export & Transport"),
    ABOUT("About Us"),
    CONTACT("Contact"),
    QUOTE("Request Quote"),
    ADMIN("Admin Portal"),
    AUTH("Account")
}

data class AuthUser(
    val name: String,
    val email: String,
    val isGoogleUser: Boolean = false
)

data class QuoteFormState(
    val category: String = ProductCategory.VEGETABLES.displayName,
    val productName: String = "",
    val quantity: String = "",
    val country: String = "",
    val preferredDeliveryDate: String = "",
    val customerName: String = "",
    val companyName: String = "",
    val mobile: String = "",
    val email: String = "",
    val additionalRequirements: String = "",
    val isSubmitting: Boolean = false
)

class VgaViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: VgaRepository

    init {
        val db = AppDatabase.getDatabase(application)
        repository = VgaRepository(db.productDao(), db.inquiryDao())

        viewModelScope.launch {
            repository.ensureDefaultProductsLoaded(InitialData.getDefaultProducts())
        }
    }

    val allProducts: StateFlow<List<ProductEntity>> = repository.allProducts
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val allInquiries: StateFlow<List<InquiryEntity>> = repository.allInquiries
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _currentTab = MutableStateFlow(NavTab.HOME)
    val currentTab: StateFlow<NavTab> = _currentTab.asStateFlow()

    private val _selectedCategoryFilter = MutableStateFlow("All")
    val selectedCategoryFilter: StateFlow<String> = _selectedCategoryFilter.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _isAdminMode = MutableStateFlow(false)
    val isAdminMode: StateFlow<Boolean> = _isAdminMode.asStateFlow()

    private val _editingProduct = MutableStateFlow<ProductEntity?>(null)
    val editingProduct: StateFlow<ProductEntity?> = _editingProduct.asStateFlow()

    private val _showProductEditDialog = MutableStateFlow(false)
    val showProductEditDialog: StateFlow<Boolean> = _showProductEditDialog.asStateFlow()

    private val _quoteForm = MutableStateFlow(QuoteFormState())
    val quoteForm: StateFlow<QuoteFormState> = _quoteForm.asStateFlow()

    private val _currentUser = MutableStateFlow<AuthUser?>(null)
    val currentUser: StateFlow<AuthUser?> = _currentUser.asStateFlow()

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent = _toastEvent.asSharedFlow()

    // Filtered products flow based on category and search query
    val filteredProducts: StateFlow<List<ProductEntity>> = combine(
        allProducts,
        _selectedCategoryFilter,
        _searchQuery
    ) { products, category, query ->
        products.filter { product ->
            val matchesCategory = if (category == "All") true else product.category.equals(category, ignoreCase = true)
            val matchesQuery = query.isBlank() ||
                    product.name.contains(query, ignoreCase = true) ||
                    product.description.contains(query, ignoreCase = true) ||
                    product.variety.contains(query, ignoreCase = true)
            matchesCategory && matchesQuery
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun navigateTo(tab: NavTab) {
        _currentTab.value = tab
    }

    fun signIn(email: String, name: String? = null, isGoogle: Boolean = false) {
        val resolvedName = name?.ifBlank { null }
            ?: email.substringBefore("@").replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        _currentUser.value = AuthUser(name = resolvedName, email = email, isGoogleUser = isGoogle)
        // Also prefill quote form if empty
        if (_quoteForm.value.customerName.isBlank()) {
            _quoteForm.value = _quoteForm.value.copy(
                customerName = resolvedName,
                email = email
            )
        }
        viewModelScope.launch {
            _toastEvent.emit("Signed in as $resolvedName")
        }
    }

    fun signUp(name: String, email: String) {
        _currentUser.value = AuthUser(name = name, email = email, isGoogleUser = false)
        if (_quoteForm.value.customerName.isBlank()) {
            _quoteForm.value = _quoteForm.value.copy(
                customerName = name,
                email = email
            )
        }
        viewModelScope.launch {
            _toastEvent.emit("Account created! Welcome, $name")
        }
    }

    fun signOut() {
        _currentUser.value = null
        viewModelScope.launch {
            _toastEvent.emit("Signed out successfully")
        }
    }

    fun setCategoryFilter(category: String) {
        _selectedCategoryFilter.value = category
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun toggleAdminMode() {
        _isAdminMode.value = !_isAdminMode.value
    }

    fun updateQuoteForm(update: (QuoteFormState) -> QuoteFormState) {
        _quoteForm.value = update(_quoteForm.value)
    }

    fun prefillQuoteForProduct(product: ProductEntity) {
        _quoteForm.value = _quoteForm.value.copy(
            category = product.category,
            productName = product.name,
            quantity = product.minOrder
        )
        _currentTab.value = NavTab.QUOTE
    }

    fun submitQuoteForm(onSuccess: (InquiryEntity) -> Unit) {
        val form = _quoteForm.value
        if (form.productName.isBlank() || form.customerName.isBlank() || (form.mobile.isBlank() && form.email.isBlank())) {
            viewModelScope.launch {
                _toastEvent.emit("Please enter product name, your name, and phone or email.")
            }
            return
        }

        viewModelScope.launch {
            val inquiry = InquiryEntity(
                category = form.category,
                productName = form.productName,
                quantity = if (form.quantity.isBlank()) "Standard Export Qty" else form.quantity,
                country = if (form.country.isBlank()) "International" else form.country,
                preferredDeliveryDate = form.preferredDeliveryDate,
                customerName = form.customerName,
                companyName = form.companyName,
                phone = form.mobile,
                email = form.email,
                additionalRequirements = form.additionalRequirements,
                status = "Pending"
            )
            repository.submitInquiry(inquiry)
            _quoteForm.value = QuoteFormState() // Reset
            _toastEvent.emit("Export quote inquiry submitted successfully! VGA Export will contact you promptly.")
            onSuccess(inquiry)
        }
    }

    fun toggleProductAvailability(product: ProductEntity) {
        viewModelScope.launch {
            repository.updateProductAvailability(product.id, !product.isAvailable)
            val status = if (!product.isAvailable) "Available" else "Unavailable"
            _toastEvent.emit("${product.name} marked as $status")
        }
    }

    fun openProductEditor(product: ProductEntity?) {
        _editingProduct.value = product
        _showProductEditDialog.value = true
    }

    fun closeProductEditor() {
        _editingProduct.value = null
        _showProductEditDialog.value = false
    }

    fun saveProduct(
        name: String,
        category: String,
        description: String,
        packingDetails: String,
        variety: String,
        minOrder: String,
        isAvailable: Boolean,
        imageType: String
    ) {
        viewModelScope.launch {
            val current = _editingProduct.value
            if (current != null) {
                val updated = current.copy(
                    name = name,
                    category = category,
                    description = description,
                    packingDetails = packingDetails,
                    variety = variety,
                    minOrder = minOrder,
                    isAvailable = isAvailable,
                    imageType = imageType
                )
                repository.updateProduct(updated)
                _toastEvent.emit("Product '${name}' updated.")
            } else {
                val newProduct = ProductEntity(
                    name = name,
                    category = category,
                    description = description,
                    packingDetails = packingDetails,
                    variety = variety,
                    minOrder = minOrder,
                    isAvailable = isAvailable,
                    imageType = imageType
                )
                repository.insertProduct(newProduct)
                _toastEvent.emit("Product '${name}' added to catalog.")
            }
            closeProductEditor()
        }
    }

    fun deleteProduct(product: ProductEntity) {
        viewModelScope.launch {
            repository.deleteProduct(product.id)
            _toastEvent.emit("Product '${product.name}' removed.")
        }
    }

    fun updateInquiryStatus(inquiryId: Int, status: String) {
        viewModelScope.launch {
            repository.updateInquiryStatus(inquiryId, status)
            _toastEvent.emit("Inquiry status updated to $status")
        }
    }

    fun deleteInquiry(inquiryId: Int) {
        viewModelScope.launch {
            repository.deleteInquiry(inquiryId)
            _toastEvent.emit("Inquiry removed.")
        }
    }
}
