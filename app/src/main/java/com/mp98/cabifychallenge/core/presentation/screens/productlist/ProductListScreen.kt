package com.mp98.cabifychallenge.core.presentation.screens.productlist

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.mp98.cabifychallenge.core.presentation.screens.productlist.components.ProductItem
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel

@Composable
fun ProductList(productCartViewModel: ProductCartViewModel = hiltViewModel()){
    val gridState = rememberLazyGridState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = gridState,
        content = {
            items(items = productCartViewModel.productsCartState.products,
                contentType = { it }
            ){
                ProductItem(it){

                }
            }
        }
    )
}