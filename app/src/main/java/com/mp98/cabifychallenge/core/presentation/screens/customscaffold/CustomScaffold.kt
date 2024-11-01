package com.mp98.cabifychallenge.core.presentation.screens.customscaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ShoppingBag
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mp98.cabifychallenge.core.presentation.screens.navigation.NavigationRoute
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel
import com.mp98.cabifychallenge.core.utils.dynamicPadding

@Composable
fun CustomScaffold(
    modifier: Modifier,
    productCartViewModel: ProductCartViewModel,
    content: @Composable () -> Unit,
    onChangeToCart: () -> Unit,
    onChangeToProducts: () -> Unit
) {

    val state by productCartViewModel.productsCartState.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(productCartViewModel = productCartViewModel){
                productCartViewModel.changeScreen(NavigationRoute.CartListScreen)
                onChangeToCart()
            }
        },
        floatingActionButton = {
            if(state.screen == NavigationRoute.CartListScreen) {
                Box(Modifier.dynamicPadding()){
                    FloatingActionButton(
                        contentColor = Color.White,
                        shape = RoundedCornerShape(50.dp),
                        onClick = {
                            productCartViewModel.changeScreen(NavigationRoute.ProductListScreen)
                            onChangeToProducts()
                        },
                        modifier = Modifier.align(Alignment.BottomEnd)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ShoppingBag,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(fraction = 0.05f)
                        )
                    }
                }
            }
        }
    ){ padding ->
        Column(modifier = Modifier
            .padding(padding).fillMaxSize()) {
            HorizontalDivider(
                thickness = 0.5.dp
            )
            content()
        }
    }
}