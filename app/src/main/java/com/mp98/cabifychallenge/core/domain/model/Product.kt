package com.mp98.cabifychallenge.core.domain.model

data class Product(
    val code: String,
    val name: String,
    val price: Double,
    val discount: String? = null,
    val discountPrice: Double = price,
    val minQuantity: Int = 0
)