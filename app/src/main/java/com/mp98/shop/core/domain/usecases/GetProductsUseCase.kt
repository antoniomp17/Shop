package com.mp98.shop.core.domain.usecases

import com.mp98.shop.core.domain.model.Product
import com.mp98.shop.core.domain.repositories.ProductRepository

class GetProductsUseCase(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(): List<Product> {
        return productRepository.getProducts()
    }
}