package com.mp98.cabifychallenge.core.presentation.screens.productlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.mp98.cabifychallenge.core.domain.cart.discount.DiscountType
import com.mp98.cabifychallenge.core.presentation.states.ProductsCartState
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel
import com.mp98.cabifychallenge.core.utils.scalableText
import com.mp98.cabifychallenge.ui.theme.primaryColor

@Composable
fun DiscountDialog(state: ProductsCartState, productCartViewModel: ProductCartViewModel){

    if(!state.showDiscountDialog.isNullOrEmpty()){
        AlertDialog(
            modifier = Modifier.fillMaxWidth(),
            onDismissRequest = {
                productCartViewModel.changeShowDiscountDialog("")
            },
            title = {
                Text(
                    text = DiscountType.getDiscountString(state.showDiscountDialog),
                    color = primaryColor
                )
            },
            text = {
                Text(
                    text =  DiscountType.getDiscountDescriptionString(state.showDiscountDialog),
                    fontSize = scalableText(16.sp),
                    textAlign = TextAlign.Justify
                )
            },
            confirmButton = {
                Button(
                    onClick = { productCartViewModel.changeShowDiscountDialog("")}
                ) {
                    Text(text = "Aceptar")
                }
            }
        )
    }
}