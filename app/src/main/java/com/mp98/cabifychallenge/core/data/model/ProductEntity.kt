package com.mp98.cabifychallenge.core.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ProductEntity")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val code: String,
    val name: String,
    val price: Double,
    val discount: String? = null,
    val discountPrice: Double = price,
    val minQuantity: Int = 0
)