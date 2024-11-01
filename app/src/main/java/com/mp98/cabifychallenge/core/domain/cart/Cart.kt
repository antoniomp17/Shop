package com.mp98.cabifychallenge.core.domain.cart

import com.mp98.cabifychallenge.core.domain.cart.discount.Discount
import com.mp98.cabifychallenge.core.domain.model.Product

data class Cart(
    private val discounts: List<Discount?>,
    val items: List<Product> = listOf(),
    val total: Double = 0.0
) {

    fun addProduct(product: Product): Cart {
        val newCart = this.copy(
            items = this.items + product)
        return newCart.copy(total = newCart.calculateTotal())
    }

    fun removeProduct(product: Product): Cart {
        val newCart = this.copy(
            items = this.items -  product)
        return newCart.copy(total = newCart.calculateTotal())
    }

    fun setDiscounts(discounts: List<Discount?>): Cart {
        return this.copy(discounts = discounts)
    }

    fun getProductsOfCode(code: String): List<Product> {
        return items.filter { it.code == code }
    }

    private fun calculateTotal(): Double {
        var total = items.sumOf { it.price }

        for (discount in discounts) {
            total -= discount?.applyDiscount(items) ?: 0.0
        }
        return total
    }
}