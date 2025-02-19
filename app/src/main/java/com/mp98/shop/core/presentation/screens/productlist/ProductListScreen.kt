package com.mp98.shop.core.presentation.screens.productlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.mp98.shop.core.presentation.screens.productlist.components.ProductItem
import com.mp98.shop.core.presentation.viewmodels.ProductCartViewModel

@Composable
fun ProductList(productCartViewModel: ProductCartViewModel){

    val state by productCartViewModel.productsCartState.collectAsState()

    val gridState = rememberLazyGridState()
    Column(
        modifier = Modifier.testTag("ProductList").fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        if(state.name.isNotEmpty()){
            Text(
                text = "Welcome ${state.name}",
                style = MaterialTheme.typography.titleSmall
            )
        }
        LazyVerticalGrid(
            modifier = Modifier.testTag("ProductGrid"),
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
    }
    DiscountDialog(productCartViewModel)
}