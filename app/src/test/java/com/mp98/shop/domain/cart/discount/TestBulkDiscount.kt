package com.mp98.shop.domain.cart.discount

import com.mp98.shop.core.domain.cart.discount.BulkDiscount
import com.mp98.shop.core.domain.cart.discount.DiscountType
import com.mp98.shop.core.domain.model.Product
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TestBulkDiscount {

    private lateinit var product: Product
    private lateinit var bulkDiscount: BulkDiscount

    @Before
    fun setUp() {
        product = Product(
            code = "TSHIRT",
            name = "Test Shirt",
            price = 20.0,
            discount = DiscountType.BULK_DISCOUNT,
            discountPrice = 19.0,
            minQuantity = 3
        )
        bulkDiscount = BulkDiscount(product)
    }

    @Test
    fun `applyDiscount applies bulk discount when quantity is sufficient`() {
        val items = listOf(product, product, product)

        val discount = bulkDiscount.applyDiscount(items)

        val expectedDiscount = 3 * (product.price - product.discountPrice) // 4 * (20.0 - 19.0) = 3.0
        assertEquals(expectedDiscount, discount, 0.0)
    }

    @Test
    fun `applyDiscount does not apply discount when quantity is insufficient`() {
        val items = listOf(product, product)

        val discount = bulkDiscount.applyDiscount(items)

        val expectedDiscount = 0.0
        assertEquals(expectedDiscount, discount, 0.0)
    }

    @Test
    fun `applyDiscount ignores products with different codes`() {
        val differentProduct = product.copy(code = "MUG")
        val items = listOf(product, product, differentProduct)

        val discount = bulkDiscount.applyDiscount(items)

        val expectedDiscount = 0.0
        assertEquals(expectedDiscount, discount, 0.0)
    }

    @Test
    fun `applyDiscount applies partial bulk discount when exact quantity meets threshold`() {
        val items = listOf(product, product, product, product)

        val discount = bulkDiscount.applyDiscount(items)

        val expectedDiscount = 4 * (product.price - product.discountPrice) // 4 * (20.0 - 19.0) = 4.0
        assertEquals(expectedDiscount, discount, 0.0)
    }
}