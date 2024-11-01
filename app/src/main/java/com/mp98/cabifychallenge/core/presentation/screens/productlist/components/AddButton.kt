package com.mp98.cabifychallenge.core.presentation.screens.productlist.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mp98.cabifychallenge.R
import com.mp98.cabifychallenge.core.domain.model.Product
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel
import com.mp98.cabifychallenge.core.utils.dynamicPadding
import com.mp98.cabifychallenge.core.utils.scalableText

@Composable
fun AddButton(product: Product,
              productCartViewModel: ProductCartViewModel,
              modifier: Modifier = Modifier){
    Button(
        onClick = {
            productCartViewModel.addProductToCart(product)
        },
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .dynamicPadding()
            .aspectRatio(3f)
            .fillMaxWidth(fraction = 0.75f)
    ) {
        Text(
            text = stringResource(id = R.string.add_item),
            style = TextStyle(
                fontSize = scalableText(16.sp),
                fontWeight = FontWeight.Bold
            )
        )
    }
}