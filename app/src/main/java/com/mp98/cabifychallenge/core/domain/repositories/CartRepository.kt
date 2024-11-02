package com.mp98.cabifychallenge.core.domain.repositories

import com.mp98.cabifychallenge.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getAllProducts(): Flow<List<Product>>
    suspend fun insertProduct(product: Product)
    suspend fun removeProduct(code: String)
}