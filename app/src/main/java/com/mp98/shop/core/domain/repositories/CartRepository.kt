package com.mp98.shop.core.domain.repositories

import com.mp98.shop.core.domain.model.ProductCart
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getAllProducts(): Flow<List<ProductCart>>
    suspend fun insertProduct(product: ProductCart)
    suspend fun removeProduct(code: String)
}