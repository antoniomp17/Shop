package com.mp98.cabifychallenge.core.domain.cart.discount

import com.mp98.cabifychallenge.core.domain.model.Product

sealed class DiscountType {
    companion object {
        fun fromDiscount(product: Product): Discount? {
            return when (product.discount) {
                "2X1" -> TwoForOneDiscount(product)
                "VOUCHER" -> BulkDiscount(product)
                else -> null
            }
        }
    }
}