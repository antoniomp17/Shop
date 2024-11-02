package com.mp98.cabifychallenge.core.presentation.screens.customscaffold.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.mp98.cabifychallenge.R
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel
import com.mp98.cabifychallenge.core.utils.scalableText
import com.mp98.cabifychallenge.core.utils.toCurrencyFormat
import com.mp98.cabifychallenge.ui.theme.errorColor
import com.mp98.cabifychallenge.ui.theme.tertiaryColor

@Composable
fun BottomBar(productCartViewModel: ProductCartViewModel){

    val state by productCartViewModel.productsCartState.collectAsState()

    BottomAppBar{
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically){
            Text(
                text =  buildAnnotatedString {
                    append(stringResource(id = R.string.antes) + " ")
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                        append(productCartViewModel.getTotalWithDiscount().toCurrencyFormat())

                    }
                },
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = scalableText(16.sp),
                modifier = Modifier.weight(1f),
                color = errorColor
            )

            Text(
                text = stringResource(id = R.string.total) + " " +
                        state.cart.total.toCurrencyFormat(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = scalableText(16.sp),
                modifier = Modifier.weight(1f),
                color = tertiaryColor
            )
        }
    }
}