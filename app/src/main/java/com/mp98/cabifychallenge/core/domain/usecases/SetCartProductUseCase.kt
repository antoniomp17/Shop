package com.mp98.cabifychallenge.core.domain.usecases

import com.mp98.cabifychallenge.core.domain.model.Product
import com.mp98.cabifychallenge.core.domain.repositories.CartRepository

class SetCartProductUseCase(private val repository: CartRepository) {
    suspend operator fun invoke(product: Product){
        return repository.insertProduct(product)
    }
}