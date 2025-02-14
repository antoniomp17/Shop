package com.mp98.shop.core.presentation.screens.cartlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mp98.shop.R
import com.mp98.shop.core.presentation.screens.cartlist.components.CartItem
import com.mp98.shop.core.presentation.screens.productlist.DiscountDialog
import com.mp98.shop.core.presentation.viewmodels.ProductCartViewModel
import com.mp98.shop.core.utils.dynamicPadding
import com.mp98.shop.core.utils.scalableText
import com.mp98.shop.ui.theme.primaryColor

@Composable
fun CartList(productCartViewModel: ProductCartViewModel){

    val state by productCartViewModel.productsCartState.collectAsState()

    val gridState = rememberLazyGridState()

    if(state.cart.items.isEmpty()){
        Box(
            modifier = Modifier.fillMaxSize().dynamicPadding(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = stringResource(R.string.empty_cart_message),
                fontSize = scalableText(22.sp),
                fontWeight = FontWeight.Bold,
                color = primaryColor
            )
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            state = gridState,
            content = {
                items(items = state.cart.items.distinct().sortedBy { it.code },
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
        DiscountDialog(productCartViewModel)
    }
}