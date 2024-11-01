package com.mp98.cabifychallenge.core.presentation.screens.productlist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mp98.cabifychallenge.core.domain.model.Product
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel
import com.mp98.cabifychallenge.core.utils.dynamicPadding

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