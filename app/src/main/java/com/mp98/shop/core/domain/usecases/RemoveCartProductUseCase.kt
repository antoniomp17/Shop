package com.mp98.shop.core.domain.usecases

import com.mp98.shop.core.domain.repositories.CartRepository

class RemoveCartProductUseCase (private val repository: CartRepository) {
    suspend operator fun invoke(code: String) {
        return repository.removeProduct(code)
    }
}