package com.mp98.cabifychallenge.domain.cart.discount

import com.mp98.cabifychallenge.core.domain.cart.discount.DiscountType
import com.mp98.cabifychallenge.core.domain.cart.discount.TwoForOneDiscount
import com.mp98.cabifychallenge.core.domain.model.Product
import junit.framework.TestCase.assertEquals
import org.junit.Test

class TestTwoForOneDiscount {

    private val product = Product(
        code = "VOUCHER",
        name = "Voucher",
        price = 10.0,
        discount = DiscountType.TWO_FOR_ONE_DISCOUNT
    )

    private val discount = TwoForOneDiscount(product)

    @Test
    fun `applyDiscount with no eligible products returns zero discount`() {
        val items = listOf(
            Product(code = "T_SHIRT", name = "T-Shirt", price = 20.0)
        )

        val result = discount.applyDiscount(items)

        assertEquals(0.0, result, 0.0)
    }

    @Test
    fun `applyDiscount with one eligible product returns zero discount`() {
        val items = listOf(product)

        val result = discount.applyDiscount(items)

        assertEquals(0.0, result, 0.0)
    }

    @Test
    fun `applyDiscount with two eligible products returns one product price as discount`() {
        val items = listOf(product, product)

        val result = discount.applyDiscount(items)

        assertEquals(10.0, result, 0.0)
    }

    @Test
    fun `applyDiscount with three eligible products returns one product price as discount`() {
        val items = listOf(product, product, product)

        val result = discount.applyDiscount(items)

        assertEquals(10.0, result, 0.0)
    }

    @Test
    fun `applyDiscount with four eligible products returns two products prices as discount`() {
        val items = listOf(product, product, product, product)

        val result = discount.applyDiscount(items)

        assertEquals(20.0, result, 0.0)
    }

}