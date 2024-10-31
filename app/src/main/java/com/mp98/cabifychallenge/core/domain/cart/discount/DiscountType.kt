package com.mp98.cabifychallenge.core.domain.cart.discount

sealed class DiscountType(val discount: String) {
    data object TwoForOne : DiscountType("2X1")
    data object Bulk: DiscountType("BULK")
}