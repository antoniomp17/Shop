package com.mp98.cabifychallenge.core.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mp98.cabifychallenge.core.data.model.ProductCartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Query("SELECT * FROM ProductCartEntity")
    fun getAllProducts(): Flow<List<ProductCartEntity>>

    @Insert
    suspend fun insertProduct(product: ProductCartEntity)

    @Query("DELETE FROM ProductCartEntity WHERE id = " +
            "(SELECT id FROM ProductCartEntity WHERE code = :code LIMIT 1)")
    suspend fun removeProduct(code: String)
}