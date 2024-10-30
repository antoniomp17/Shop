package com.mp98.cabifychallenge.core.presentation.models

sealed class ProductImage(val imageUrl: String) {
    data object TShirt : ProductImage("https://i.ibb.co/hX5QD3s/camiseta-cabify.png")
    data object Voucher : ProductImage("https://i.ibb.co/TkrwWjc/voucher-cabify.png")
    object Mug : ProductImage("https://i.ibb.co/7QfjDzk/cabify-mug.png")

    companion object {
        fun fromId(id: String): ProductImage? {
            return when (id) {
                "TSHIRT" -> TShirt
                "VOUCHER" -> Voucher
                "MUG" -> Mug
                else -> null
            }
        }
    }
}