package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.InquiryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InquiryDao {
    @Query("SELECT * FROM inquiries ORDER BY timestamp DESC")
    fun getAllInquiries(): Flow<List<InquiryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInquiry(inquiry: InquiryEntity): Long

    @Update
    suspend fun updateInquiry(inquiry: InquiryEntity)

    @Query("UPDATE inquiries SET status = :status WHERE id = :id")
    suspend fun updateStatus(id: Int, status: String)

    @Delete
    suspend fun deleteInquiry(inquiry: InquiryEntity)

    @Query("DELETE FROM inquiries WHERE id = :id")
    suspend fun deleteInquiryById(id: Int)
}
