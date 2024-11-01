package com.mp98.cabifychallenge.core.presentation.screens.productlist.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.mp98.cabifychallenge.core.domain.model.Product
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel

@Composable
fun ItemButton(
    product: Product,
    productCartViewModel: ProductCartViewModel
) {

    val state by productCartViewModel.productsCartState.collectAsState()

    if(state.cart.items.contains(product)){
        AddOrRemoveButton(product, productCartViewModel, state)
    } else {
        AddButton(product, productCartViewModel)
    }
}