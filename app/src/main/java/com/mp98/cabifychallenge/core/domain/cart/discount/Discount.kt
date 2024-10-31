package com.mp98.cabifychallenge.core.domain.cart.discount

import com.mp98.cabifychallenge.core.domain.model.Product

interface Discount {
    fun applyDiscount(items: List<Product>): Double
}