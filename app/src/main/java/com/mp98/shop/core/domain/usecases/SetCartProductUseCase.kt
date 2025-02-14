package com.mp98.shop.core.domain.usecases

import com.mp98.shop.core.domain.model.ProductCart
import com.mp98.shop.core.domain.repositories.CartRepository

class SetCartProductUseCase(private val repository: CartRepository) {
    suspend operator fun invoke(product: ProductCart){
        return repository.insertProduct(product)
    }
}