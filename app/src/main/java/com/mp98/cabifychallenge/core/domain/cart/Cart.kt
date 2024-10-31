package com.mp98.cabifychallenge.core.domain.cart

import com.mp98.cabifychallenge.core.domain.cart.discount.Discount
import com.mp98.cabifychallenge.core.domain.model.Product

class Cart(private val discounts: List<Discount?>) {

    private val items = mutableListOf<Product>()

    fun addProduct(product: Product) {
        items.add(product)
    }

    fun calculateTotal(): Double {
        var total = items.sumOf { it.price }

        for (discount in discounts) {
            total -= discount?.applyDiscount(items) ?: 0.0
        }
        return total
    }
}