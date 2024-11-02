package com.mp98.cabifychallenge.core.domain.usecases

import com.mp98.cabifychallenge.core.domain.model.ProductCart
import com.mp98.cabifychallenge.core.domain.repositories.CartRepository

class SetCartProductUseCase(private val repository: CartRepository) {
    suspend operator fun invoke(product: ProductCart){
        return repository.insertProduct(product)
    }
}