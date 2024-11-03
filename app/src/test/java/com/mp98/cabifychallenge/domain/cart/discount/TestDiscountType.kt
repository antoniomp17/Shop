package com.mp98.cabifychallenge.domain.cart.discount

import com.mp98.cabifychallenge.core.domain.cart.discount.BulkDiscount
import com.mp98.cabifychallenge.core.domain.cart.discount.DiscountType
import com.mp98.cabifychallenge.core.domain.cart.discount.TwoForOneDiscount
import com.mp98.cabifychallenge.core.domain.model.Product
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class TestDiscountType {

    @Test
    fun `fromDiscount returns TwoForOneDiscount for 2X1 discount`() {
        val product = Product(
            code = "VOUCHER",
            name = "Test Voucher",
            price = 10.0,
            discount = DiscountType.TWO_FOR_ONE_DISCOUNT
        )

        val discount = DiscountType.fromDiscount(product)

        assertTrue(discount is TwoForOneDiscount)
    }

    @Test
    fun `fromDiscount returns BulkDiscount for BULK discount`() {
        val product = Product(
            code = "TSHIRT",
            name = "Test T-Shirt",
            price = 20.0,
            discount = DiscountType.BULK_DISCOUNT,
            discountPrice = 19.0,
            minQuantity = 3
        )

        val discount = DiscountType.fromDiscount(product)

        assertTrue(discount is BulkDiscount)
    }

    @Test
    fun `fromDiscount returns null for unsupported discount`() {
        val product = Product(
            code = "MUG",
            name = "Test Mug",
            price = 5.0,
            discount = "UNSUPPORTED_DISCOUNT"
        )

        val discount = DiscountType.fromDiscount(product)

        assertNull(discount)
    }
}