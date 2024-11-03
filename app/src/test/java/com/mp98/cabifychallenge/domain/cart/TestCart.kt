package com.mp98.cabifychallenge.domain.cart

import com.mp98.cabifychallenge.core.domain.cart.Cart
import com.mp98.cabifychallenge.core.domain.cart.discount.Discount
import com.mp98.cabifychallenge.core.domain.model.Product
import com.mp98.cabifychallenge.core.domain.model.ProductCart
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class TestCart {

    private lateinit var product1: Product
    private lateinit var product2: Product
    private lateinit var mockDiscount1: Discount
    private lateinit var mockDiscount2: Discount

    @Before
    fun setUp() {
        product1 = Product(code = "VOUCHER", name = "Voucher", price = 10.0)
        product2 = Product(code = "TSHIRT", name = "T-Shirt", price = 20.0)

        mockDiscount1 = mock(Discount::class.java)
        mockDiscount2 = mock(Discount::class.java)
    }

    @Test
    fun `setItems sets items correctly`() {
        val productsCart = listOf(ProductCart("VOUCHER"), ProductCart("VOUCHER"), ProductCart("TSHIRT"))
        val availableProducts = listOf(product1, product2)
        val cart = Cart(discounts = listOf())

        val updatedCart = cart.setItems(productsCart, availableProducts)

        assertEquals(3, updatedCart.items.size)
        assertEquals(listOf(product1, product1, product2), updatedCart.items)
    }

    @Test
    fun `setDiscounts applies discounts and updates total`() {
        val cart = Cart(discounts = listOf(), items = listOf(product1, product2))

        `when`(mockDiscount1.applyDiscount(cart.items)).thenReturn(5.0)
        val updatedCart = cart.setDiscounts(listOf(mockDiscount1))

        val total = updatedCart.total

        assertEquals(25.0, total, 0.0)
        verify(mockDiscount1).applyDiscount(cart.items)
    }

    @Test
    fun `getProductsOfCode returns only products with specific code`() {
        val cart = Cart(
            discounts = listOf(),
            items = listOf(product1, product1, product2)
        )

        val result = cart.getProductsOfCode("VOUCHER")

        assertEquals(2, result.size)
        assertEquals(listOf(product1, product1), result)
    }

    @Test
    fun `getTotalOfProduct returns correct total for specific product code`() {
        val cart = Cart(
            discounts = listOf(),
            items = listOf(product1, product1, product2)
        )

        val result = cart.getTotalOfProduct("VOUCHER")

        assertEquals(20.0, result, 0.0)
    }

    @Test
    fun `calculateTotalWithDiscount without discounts returns sum of item prices`() {
        val cart = Cart(
            discounts = listOf(),
            items = listOf(product1, product1, product2)
        )

        val result = cart.calculateTotalWithDiscount()

        assertEquals(40.0, result, 0.0)
    }

    @Test
    fun `calculateTotal applies multiple discounts`() {
        val cart = Cart(
            discounts = listOf(mockDiscount1, mockDiscount2),
            items = listOf(product1, product1, product2)
        )

        // Configure mocks to return specific discount values
        `when`(mockDiscount1.applyDiscount(cart.items)).thenReturn(5.0)
        `when`(mockDiscount2.applyDiscount(cart.items)).thenReturn(3.0)

        val result = cart.getCalculateTotal(cart.items)

        assertEquals(32.0, result, 0.0)
        verify(mockDiscount1).applyDiscount(cart.items)
        verify(mockDiscount2).applyDiscount(cart.items)
    }

}