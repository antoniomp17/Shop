package com.mp98.shop.core.domain.repositories

import com.mp98.shop.core.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
}