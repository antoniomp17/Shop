package com.mp98.shop.core.presentation.screens.customscaffold.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.mp98.shop.core.presentation.viewmodels.ProductCartViewModel
import com.mp98.shop.core.utils.badgeLayout
import com.mp98.shop.core.utils.dynamicPadding
import com.mp98.shop.core.utils.scalableText
import com.mp98.shop.core.utils.toCurrencyFormat

@Composable
fun CartButton(
    productCartViewModel: ProductCartViewModel,
    onChangeToCart: () -> Unit
){

    val state by productCartViewModel.productsCartState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.dynamicPadding()
    ) {
        BadgedBox(
            modifier = Modifier.weight(1f),
            badge = {
                if(state.cart.items.isNotEmpty()){
                    Text(
                        text = state.cart.items.size.toString(),
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.error, shape = CircleShape)
                            .badgeLayout(),
                        color = Color.White
                    )
                }
            }
        ){
            IconButton(
                onClick =
                {
                    onChangeToCart()
                }
            )
            {
                Icon(
                    imageVector = Icons.Rounded.ShoppingCart,
                    contentDescription = Icons.Rounded.ShoppingCart.name,
                )
            }
        }

        Text(
            text = state.cart.total.toCurrencyFormat(),
            textAlign = TextAlign.Center,
            fontSize = scalableText(12.sp),
            modifier = Modifier.weight(1f)
        )
    }
}