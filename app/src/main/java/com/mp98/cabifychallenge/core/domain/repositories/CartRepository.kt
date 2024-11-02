package com.mp98.cabifychallenge.core.domain.repositories

import com.mp98.cabifychallenge.core.domain.model.Product
import com.mp98.cabifychallenge.core.domain.model.ProductCart
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getAllProducts(): Flow<List<ProductCart>>
    suspend fun insertProduct(product: ProductCart)
    suspend fun removeProduct(code: String)
}