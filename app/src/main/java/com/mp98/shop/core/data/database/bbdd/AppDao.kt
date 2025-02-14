package com.mp98.shop.core.data.database.bbdd

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mp98.shop.core.data.database.model.ProductCartEntity
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