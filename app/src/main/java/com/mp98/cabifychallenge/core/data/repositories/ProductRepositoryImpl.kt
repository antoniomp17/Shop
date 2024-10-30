package com.mp98.cabifychallenge.core.data.repositories

import com.mp98.cabifychallenge.core.data.api.ProductService
import com.mp98.cabifychallenge.core.data.mapper.toDomain
import com.mp98.cabifychallenge.core.domain.model.Product
import com.mp98.cabifychallenge.core.domain.repositories.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService
) : ProductRepository {
    override suspend fun getProducts(): List<Product> {
        return productService.getProducts().products.map { it.toDomain() }
    }
}