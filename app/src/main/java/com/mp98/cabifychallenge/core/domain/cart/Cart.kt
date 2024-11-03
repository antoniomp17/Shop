package com.mp98.cabifychallenge.core.domain.cart

import com.mp98.cabifychallenge.core.domain.cart.discount.Discount
import com.mp98.cabifychallenge.core.domain.model.Product
import com.mp98.cabifychallenge.core.domain.model.ProductCart

data class Cart(
    private val discounts: List<Discount?>,
    val items: List<Product> = listOf(),
    val total: Double = 0.0
) {

    fun setItems(productsCart: List<ProductCart>, availableProducts: List<Product>): Cart {
        val productCountMap = productsCart.groupingBy { it.code }.eachCount()

        val newItems = availableProducts.filter { productCountMap.containsKey(it.code) }
            .flatMap { product ->
                List(productCountMap[product.code] ?: 0) { product }
            }

        val newCart = this.copy(items = newItems)
        return newCart.copy(total = newCart.calculateTotal(newCart.items))
    }

    fun setDiscounts(discounts: List<Discount?>): Cart {
        val newCart = this.copy(discounts = discounts)
        return newCart.copy(total = newCart.calculateTotal(newCart.items))
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

    fun getCalculateTotal(productList: List<Product>): Double {
        return calculateTotal(productList)
    }

    private fun calculateTotal(productList: List<Product>): Double {
        var total = productList.sumOf { it.price }

        for (discount in discounts) {
            total -= discount?.applyDiscount(productList) ?: 0.0
        }
        return total
    }
}