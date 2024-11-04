package com.mp98.cabifychallenge.core.presentation.screens.customscaffold.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mp98.cabifychallenge.R
import com.mp98.cabifychallenge.core.presentation.screens.navigation.NavigationRoute
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel
import com.mp98.cabifychallenge.core.utils.dynamicPadding
import com.mp98.cabifychallenge.core.utils.scalableText
import com.mp98.cabifychallenge.ui.theme.primaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    productCartViewModel: ProductCartViewModel,
    onChangeToCart: () -> Unit
){

    val state by productCartViewModel.productsCartState.collectAsState()

    CenterAlignedTopAppBar(
        modifier = Modifier
            .testTag("TopBar")
            .dynamicPadding(),
        title = {
            if(state.screen == NavigationRoute.ProductListScreen){
                SearchBar(productCartViewModel = productCartViewModel)
            } else {
                Text(
                    text = stringResource(R.string.your_purchase),
                    fontSize = scalableText(18.sp),
                    fontWeight = FontWeight.Bold,
                    color = primaryColor
                )
            }
        },
        actions = {
            if(state.screen == NavigationRoute.ProductListScreen){
                CartButton(productCartViewModel = productCartViewModel){
                    onChangeToCart()
                }
            }
        }
    )
}