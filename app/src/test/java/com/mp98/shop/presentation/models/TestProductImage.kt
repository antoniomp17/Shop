package com.mp98.shop.presentation.models

import com.mp98.shop.core.domain.model.Product
import com.mp98.shop.core.presentation.models.ProductImage
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class TestProductImage {

    @Test
    fun `TShirt image has correct URL`() {
        assertEquals("https://i.ibb.co/C3ntbRx1/shirt.png", ProductImage.TShirt.imageUrl)
    }

    @Test
    fun `Voucher image has correct URL`() {
        assertEquals("https://i.ibb.co/NnVvWQMM/voucher.png", ProductImage.Voucher.imageUrl)
    }

    @Test
    fun `Mug image has correct URL`() {
        assertEquals("https://i.ibb.co/4nTK8gZq/mug.png", ProductImage.Mug.imageUrl)
    }

    @Test
    fun `fromId returns TShirt for T_SHIRT id`() {
        val productImage = ProductImage.fromId(Product.T_SHIRT)
        assertEquals(ProductImage.TShirt, productImage)
    }

    @Test
    fun `fromId returns Voucher for VOUCHER id`() {
        val productImage = ProductImage.fromId(Product.VOUCHER)
        assertEquals(ProductImage.Voucher, productImage)
    }

    @Test
    fun `fromId returns Mug for MUG id`() {
        val productImage = ProductImage.fromId(Product.MUG)
        assertEquals(ProductImage.Mug, productImage)
    }

    @Test
    fun `fromId returns null for unknown id`() {
        val productImage = ProductImage.fromId("UNKNOWN_ID")
        assertNull(productImage)
    }

}