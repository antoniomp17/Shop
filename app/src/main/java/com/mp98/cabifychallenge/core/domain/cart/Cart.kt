package com.mp98.cabifychallenge.core.domain.cart

import com.mp98.cabifychallenge.core.domain.cart.discount.Discount
import com.mp98.cabifychallenge.core.domain.model.Product

data class Cart(
    private val discounts: List<Discount?>,
    val items: List<Product> = listOf(),
    val total: Double = 0.0
) {

    fun setItems(items: List<Product>): Cart {
        val newCart = this.copy(items = items)
        return newCart.copy(total = newCart.calculateTotal(newCart.items))
    }

    fun setDiscounts(discounts: List<Discount?>): Cart {
        return this.copy(discounts = discounts)
    }

    fun getProductsOfCode(code: String): List<Product> {
        return items.filter { it.code == code }
    }

    fun getTotalOfProduct(code: String): Double {
        return calculateTotal(items.filter { it.code == code })
    }

    fun calculateTotalWithDiscount(): Double {
        return items.sumOf { it.price }
    }

    private fun calculateTotal(productList: List<Product>): Double {
        var total = productList.sumOf { it.price }

        for (discount in discounts) {
            total -= discount?.applyDiscount(productList) ?: 0.0
        }
        return total
    }
}