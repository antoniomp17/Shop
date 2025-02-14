package com.mp98.shop.core.data.api.mapper

import com.mp98.shop.core.data.api.model.ProductEntity
import com.mp98.shop.core.domain.model.Product

fun ProductEntity.toDomain(): Product {
    return Product(code = this.code, name = this.name,
        price = this.price, discount = this.discount,
        discountPrice = this.discountPrice, minQuantity = this.minQuantity)
}

fun Product.toEntity(): ProductEntity {
    return ProductEntity(code = this.code, name = this.name,
        price = this.price, discount = this.discount,
        discountPrice = this.discountPrice, minQuantity = this.minQuantity)
}