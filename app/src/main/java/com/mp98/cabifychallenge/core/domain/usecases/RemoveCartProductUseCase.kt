package com.mp98.cabifychallenge.core.domain.usecases

import com.mp98.cabifychallenge.core.domain.model.Product
import com.mp98.cabifychallenge.core.domain.repositories.CartRepository

class RemoveCartProductUseCase (private val repository: CartRepository) {
    suspend operator fun invoke(code: String) {
        return repository.removeProduct(code)
    }
}