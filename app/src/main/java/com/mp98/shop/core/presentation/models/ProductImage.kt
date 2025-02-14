package com.mp98.shop.core.presentation.models

import com.mp98.shop.core.domain.model.Product

sealed class ProductImage(val imageUrl: String) {
    data object TShirt : ProductImage("https://i.ibb.co/pB3PfYC0/shirt.png")
    data object Voucher : ProductImage("https://i.ibb.co/NnVvWQMM/voucher.png")
    data object Mug : ProductImage("https://i.ibb.co/4nTK8gZq/mug.png")

    companion object {
        fun fromId(id: String): ProductImage? {
            return when (id) {
                Product.T_SHIRT -> TShirt
                Product.VOUCHER -> Voucher
                Product.MUG -> Mug
                else -> null
            }
        }
    }
}