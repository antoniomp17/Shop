package com.mp98.cabifychallenge.core.presentation.states

import com.mp98.cabifychallenge.core.domain.model.Product

data class ProductListState(
    val products: List<Product> = emptyList(),
)