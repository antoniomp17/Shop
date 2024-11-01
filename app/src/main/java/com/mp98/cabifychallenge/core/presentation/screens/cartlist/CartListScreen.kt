package com.mp98.cabifychallenge.core.presentation.screens.cartlist

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.mp98.cabifychallenge.core.presentation.screens.cartlist.components.CartItem
import com.mp98.cabifychallenge.core.presentation.screens.productlist.DiscountDialog
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel

@Composable
fun CartList(productCartViewModel: ProductCartViewModel){

    val state by productCartViewModel.productsCartState.collectAsState()

    val gridState = rememberLazyGridState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        state = gridState,
        content = {
            items(items = state.cart.items,
                contentType = { it }
            ){
                CartItem(
                    product =  it,
                    productCartViewModel = productCartViewModel
                ){
                    productCartViewModel.changeShowDiscountDialog(it.discount)
                }
            }
        }
    )
    DiscountDialog(state, productCartViewModel)
}