package com.mp98.cabifychallenge.presentation.models

import com.mp98.cabifychallenge.core.domain.model.Product
import com.mp98.cabifychallenge.core.presentation.models.ProductImage
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class TestProductImage {

    @Test
    fun `TShirt image has correct URL`() {
        assertEquals("https://i.ibb.co/hX5QD3s/camiseta-cabify.png", ProductImage.TShirt.imageUrl)
    }

    @Test
    fun `Voucher image has correct URL`() {
        assertEquals("https://i.ibb.co/TkrwWjc/voucher-cabify.png", ProductImage.Voucher.imageUrl)
    }

    @Test
    fun `Mug image has correct URL`() {
        assertEquals("https://i.ibb.co/7QfjDzk/cabify-mug.png", ProductImage.Mug.imageUrl)
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