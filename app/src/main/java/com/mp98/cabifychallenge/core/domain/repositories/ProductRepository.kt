package com.mp98.cabifychallenge.core.domain.repositories

import com.mp98.cabifychallenge.core.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
}