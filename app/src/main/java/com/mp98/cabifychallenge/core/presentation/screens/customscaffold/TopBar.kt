package com.mp98.cabifychallenge.core.presentation.screens.customscaffold

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mp98.cabifychallenge.core.presentation.screens.customscaffold.components.CartButton
import com.mp98.cabifychallenge.core.presentation.screens.customscaffold.components.SearchBar
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel
import com.mp98.cabifychallenge.core.utils.dynamicPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    productCartViewModel: ProductCartViewModel,
    onChangeToCart: () -> Unit
){
    CenterAlignedTopAppBar(
        modifier = Modifier.dynamicPadding(),
        title = {
            SearchBar(productCartViewModel = productCartViewModel)
        },
        actions = {
            CartButton(productCartViewModel = productCartViewModel){
                onChangeToCart()
            }
        }
    )
}