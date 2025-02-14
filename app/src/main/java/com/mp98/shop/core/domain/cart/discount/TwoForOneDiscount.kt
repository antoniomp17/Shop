package com.mp98.shop.core.domain.cart.discount

import com.mp98.shop.core.domain.model.Product

open class TwoForOneDiscount(val product: Product) : Discount {

    override fun applyDiscount(items: List<Product>): Double {
        val eligibleItems = items.filter { it.code == product.code }

        var discount = 0.0

        if(eligibleItems.size > 1){
            val odds = eligibleItems.filterIndexed { index, _ -> index % 2 != 0 }
            discount = odds.sumOf { it.price }
        }

        return discount
    }
}