package com.mp98.cabifychallenge.core.presentation.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mp98.cabifychallenge.core.domain.model.Product
import com.mp98.cabifychallenge.core.presentation.states.ProductsCartState
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel
import com.mp98.cabifychallenge.core.utils.dynamicPadding
import com.mp98.cabifychallenge.core.utils.scalableText
import com.mp98.cabifychallenge.ui.theme.secondaryColor

@Composable
fun AddOrTakeOutButton(
    product: Product,
    productCartViewModel: ProductCartViewModel,
    modifier: Modifier = Modifier
){

    val state by productCartViewModel.productsCartState.collectAsState()

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .dynamicPadding()
            .aspectRatio(3f)
            .fillMaxWidth(fraction = 0.75f)
            .background(
                color = Color(secondaryColor.value),
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                border = BorderStroke(1.dp, Color(secondaryColor.value)),
                shape = RoundedCornerShape(16.dp)
            )
    ){
        TakeOutButton(
            product = product,
            productCartViewModel = productCartViewModel,
            modifier = Modifier.dynamicPadding().weight(1f),
            state = state
        )
        Text(
            text = productCartViewModel.getProductsOfCode(product.code).size.toString(),
            modifier = Modifier.dynamicPadding().weight(1f),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = scalableText(16.sp),
                fontWeight = FontWeight.Bold
            ),
            color = Color.White)
        AddOneMoreButton(
            product = product,
            productCartViewModel = productCartViewModel,
            modifier = Modifier.dynamicPadding().weight(1f)
        )
    }
}

@Composable
private fun TakeOutButton(
    product: Product,
    productCartViewModel: ProductCartViewModel,
    modifier: Modifier = Modifier,
    state: ProductsCartState
){
    IconButton(
        onClick = {
            productCartViewModel.removeProductFromCart(product)
        },
        colors = IconButtonColors(
            containerColor = Color.Transparent,
            contentColor = Color.White,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        ),
        modifier = modifier
    ) {
        if(state.cart.items.count{it.code == product.code } > 1){
            Icon(
                imageVector = Icons.Rounded.Remove,
                contentDescription = Icons.Rounded.Remove.name
            )
        } else {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = Icons.Rounded.Delete.name
            )
        }
    }
}

@Composable
private fun AddOneMoreButton(
    product: Product,
    productCartViewModel: ProductCartViewModel,
    modifier: Modifier = Modifier
){
    IconButton(
        onClick = {
            productCartViewModel.addProductToCart(product)
        },
        colors = IconButtonColors(
            containerColor = Color.Transparent,
            contentColor = Color.White,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        ),
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Rounded.Add,
            contentDescription = Icons.Rounded.Add.name
        )
    }
}