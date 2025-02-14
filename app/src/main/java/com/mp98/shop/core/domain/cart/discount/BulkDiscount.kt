package com.mp98.shop.core.domain.cart.discount

import com.mp98.shop.core.domain.model.Product

class BulkDiscount(val product: Product) : Discount {

    override fun applyDiscount(items: List<Product>): Double {
        val eligibleItems = items.filter { it.code == product.code }
        val price = if (eligibleItems.size >= product.minQuantity){
            product.discountPrice
        } else {
            product.price
        }

        val discount = eligibleItems.size * (product.price - price)

        return discount
    }

}