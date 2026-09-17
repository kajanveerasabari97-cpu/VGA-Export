package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inquiries")
data class InquiryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val category: String,
    val productName: String,
    val quantity: String,
    val country: String,
    val preferredDeliveryDate: String,
    val customerName: String,
    val companyName: String,
    val phone: String,
    val email: String,
    val additionalRequirements: String,
    val status: String = "Pending", // "Pending", "Contacted", "Quoted", "Completed"
    val timestamp: Long = System.currentTimeMillis()
)
