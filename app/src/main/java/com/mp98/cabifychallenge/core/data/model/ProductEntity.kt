package com.mp98.cabifychallenge.core.data.model

data class ProductEntity(
    val code: String,
    val name: String,
    val price: Double,
    val discount: String? = null,
    val discountPrice: Double = price,
    val minQuantity: Int = 0
)