package com.mp98.cabifychallenge.core.domain.usecases

import com.mp98.cabifychallenge.core.domain.model.Product
import com.mp98.cabifychallenge.core.domain.repositories.CartRepository
import kotlinx.coroutines.flow.Flow

class GetAllCartProductsUseCase(private val repository: CartRepository) {
    operator fun invoke(): Flow<List<Product>>{
        return repository.getAllProducts()
    }
}