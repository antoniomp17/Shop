package com.mp98.shop.core.domain.cart.discount

import com.mp98.shop.core.domain.model.Product

interface Discount {
    fun applyDiscount(items: List<Product>): Double
}