package com.mp98.cabifychallenge.core.presentation.screens.customscaffold

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.mp98.cabifychallenge.core.presentation.screens.customscaffold.components.CartButton
import com.mp98.cabifychallenge.core.presentation.screens.customscaffold.components.SearchBar
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(productCartViewModel: ProductCartViewModel){
    CenterAlignedTopAppBar(
        title = {
            SearchBar()
        },
        actions = {
            CartButton(productCartViewModel = productCartViewModel)
        }
    )
}