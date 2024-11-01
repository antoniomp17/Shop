package com.mp98.cabifychallenge.core.presentation.screens.customscaffold.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel
import com.mp98.cabifychallenge.core.utils.badgeLayout

@Composable
fun CartButton(productCartViewModel: ProductCartViewModel){

    val state by productCartViewModel.productsCartState.collectAsState()

    BadgedBox(
        badge =
        {
            if(state.cart.items.size > 0){
                Text(
                    text = state.cart.items.size.toString(),
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.error, shape = CircleShape)
                        .badgeLayout(),
                    color = Color.White
                )
            }
        }
    )
    {
        IconButton(
            onClick =
            {

            }
        )
        {
            Icon(
                imageVector = Icons.Rounded.ShoppingCart,
                contentDescription = Icons.Rounded.ShoppingCart.name
            )
        }
    }
}