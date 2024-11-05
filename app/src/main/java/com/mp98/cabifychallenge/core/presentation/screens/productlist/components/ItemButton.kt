package com.mp98.cabifychallenge.core.presentation.screens.productlist.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.mp98.cabifychallenge.core.domain.model.Product
import com.mp98.cabifychallenge.core.presentation.screens.components.AddOrTakeOutButton
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel

@Composable
fun ItemButton(
    product: Product,
    productCartViewModel: ProductCartViewModel,
    modifier: Modifier = Modifier
) {

    val state by productCartViewModel.productsCartState.collectAsState()

    if(state.cart.items.contains(product)){
        AddOrTakeOutButton(product, productCartViewModel, modifier)
    } else {
        AddButton(product, productCartViewModel, modifier)
    }
}