package com.mp98.cabifychallenge.core.presentation.screens.productlist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mp98.cabifychallenge.R
import com.mp98.cabifychallenge.core.domain.model.Product
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel
import com.mp98.cabifychallenge.core.utils.dynamicPadding
import com.mp98.cabifychallenge.core.utils.scalableText
import com.mp98.cabifychallenge.ui.theme.CabifyPurple80

@Composable
fun ItemButton(
    product: Product,
    productCartViewModel: ProductCartViewModel
) {

    Row (horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.dynamicPadding()){

        Button(
            onClick = {
                productCartViewModel.addProductToCart(product)
            },
            colors = ButtonColors(
                containerColor = Color(CabifyPurple80.value),
                contentColor = Color.White,
                disabledContainerColor = Color(CabifyPurple80.value),
                disabledContentColor = Color.White
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
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
}