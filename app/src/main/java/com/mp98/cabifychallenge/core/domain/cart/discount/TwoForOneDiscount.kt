package com.mp98.cabifychallenge.core.domain.cart.discount

import com.mp98.cabifychallenge.core.domain.model.Product

open class TwoForOneDiscount(val product: Product) : Discount {

    override fun applyDiscount(items: List<Product>): Double {
        val eligibleItems = items.filter { it.code == product.code }

        val noDiscountPrice = eligibleItems.sumOf { it.price }
        var discount = 0.0

        if(eligibleItems.size > 1){
            val pairs = eligibleItems.filterIndexed { index, _ -> index % 2 != 0 }
            val totalDiscount = pairs.sumOf { it.price }
            discount = noDiscountPrice - totalDiscount
        }

        return discount
    }
}