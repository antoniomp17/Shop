package com.mp98.cabifychallenge.core.domain.cart.discount

import com.mp98.cabifychallenge.core.domain.model.Product

class TwoForOneDiscount(val productCode: String, private val price: Double) : Discount {

    override fun applyDiscount(items: List<Product>): Double {
        val eligibleItems = items.filter { it.code == productCode }

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