package com.mp98.cabifychallenge.core.domain.cart

import com.mp98.cabifychallenge.core.domain.cart.discount.Discount
import com.mp98.cabifychallenge.core.domain.model.Product

data class Cart(
    private val discounts: List<Discount?>,
    val items: List<Product> = listOf()
) {

    fun addProduct(product: Product): Cart {
        return this.copy(items = this.items + product)
    }

    fun calculateTotal(): Double {
        var total = items.sumOf { it.price }

        for (discount in discounts) {
            total -= discount?.applyDiscount(items) ?: 0.0
        }
        return total
    }
}