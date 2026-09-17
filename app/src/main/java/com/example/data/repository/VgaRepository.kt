package com.example.data.repository

import com.example.data.local.InquiryDao
import com.example.data.local.ProductDao
import com.example.data.model.InquiryEntity
import com.example.data.model.ProductEntity
import kotlinx.coroutines.flow.Flow

class VgaRepository(
    private val productDao: ProductDao,
    private val inquiryDao: InquiryDao
) {
    val allProducts: Flow<List<ProductEntity>> = productDao.getAllProducts()
    val allInquiries: Flow<List<InquiryEntity>> = inquiryDao.getAllInquiries()

    fun getProductsByCategory(category: String): Flow<List<ProductEntity>> {
        return productDao.getProductsByCategory(category)
    }

    suspend fun getProductById(id: Int): ProductEntity? {
        return productDao.getProductById(id)
    }

    suspend fun insertProduct(product: ProductEntity): Long {
        return productDao.insertProduct(product)
    }

    suspend fun updateProduct(product: ProductEntity) {
        productDao.updateProduct(product)
    }

    suspend fun updateProductAvailability(id: Int, isAvailable: Boolean) {
        productDao.updateAvailability(id, isAvailable)
    }

    suspend fun deleteProduct(id: Int) {
        productDao.deleteProductById(id)
    }

    suspend fun submitInquiry(inquiry: InquiryEntity): Long {
        return inquiryDao.insertInquiry(inquiry)
    }

    suspend fun updateInquiryStatus(id: Int, status: String) {
        inquiryDao.updateStatus(id, status)
    }

    suspend fun deleteInquiry(id: Int) {
        inquiryDao.deleteInquiryById(id)
    }

    suspend fun ensureDefaultProductsLoaded(defaultProducts: List<ProductEntity>) {
        val count = productDao.getProductCount()
        if (count == 0) {
            productDao.insertAll(defaultProducts)
        }
    }
}
