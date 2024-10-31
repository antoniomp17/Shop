package com.mp98.cabifychallenge.core.presentation.states

import com.mp98.cabifychallenge.core.domain.cart.Cart
import com.mp98.cabifychallenge.core.domain.model.Product

data class ProductsCartState(

    val products: List<Product> = emptyList(),
    val cart: Cart = Cart(emptyList()),
    val total: Double = 0.0

)
