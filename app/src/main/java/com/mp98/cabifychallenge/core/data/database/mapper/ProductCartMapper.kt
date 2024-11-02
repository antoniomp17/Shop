package com.mp98.cabifychallenge.core.data.database.mapper

import com.mp98.cabifychallenge.core.data.database.model.ProductCartEntity
import com.mp98.cabifychallenge.core.domain.model.ProductCart

fun ProductCartEntity.toDomain(): ProductCart {
    return ProductCart(code = this.code)
}

fun ProductCart.toEntity(): ProductCartEntity {
    return ProductCartEntity(code = this.code)
}