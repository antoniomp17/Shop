package com.mp98.cabifychallenge.core.presentation.screens.productlist

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.mp98.cabifychallenge.core.presentation.screens.productlist.components.ProductItem
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel

@Composable
fun ProductList(productCartViewModel: ProductCartViewModel){

    val state by productCartViewModel.productsCartState.collectAsState()

    val gridState = rememberLazyGridState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = gridState,
        content = {
            items(items = state.filteredProducts,
                contentType = { it }
            ){
                ProductItem(
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