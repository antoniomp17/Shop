package com.mp98.cabifychallenge.core.data.mapper

import com.mp98.cabifychallenge.core.data.model.ProductEntity
import com.mp98.cabifychallenge.core.domain.model.Product

fun ProductEntity.toDomain(): Product {
    return Product(code = code, name = name, price = price)
}