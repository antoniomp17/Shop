package com.mp98.cabifychallenge.core.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mp98.cabifychallenge.core.data.model.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Query("SELECT * FROM ProductEntity")
    fun getAllProducts(): Flow<List<ProductEntity>>

    @Insert
    suspend fun insertProduct(product: ProductEntity)

    @Query("DELETE FROM ProductEntity WHERE id = " +
            "(SELECT id FROM ProductEntity WHERE code = :code LIMIT 1)")
    suspend fun removeProduct(code: String)
}