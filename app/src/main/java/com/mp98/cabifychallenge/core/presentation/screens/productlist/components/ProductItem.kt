package com.mp98.cabifychallenge.core.presentation.screens.productlist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mp98.cabifychallenge.core.domain.model.Product
import com.mp98.cabifychallenge.core.presentation.models.ProductImage
import com.mp98.cabifychallenge.core.utils.dynamicPadding
import com.mp98.cabifychallenge.core.utils.scalableText
import com.mp98.cabifychallenge.core.utils.toCurrencyFormat

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductItem(
    product: Product,
    onSelectProduct: () -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth(fraction = 0.5f)
            .aspectRatio(0.6f)
            .dynamicPadding(),
        shape = ShapeDefaults.Large,
        onClick = {
            onSelectProduct()
        }
    ){
        Column (modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){

            GlideImage(
                modifier = Modifier
                    .dynamicPadding()
                    .fillMaxSize(fraction = 0.4f),
                model = ProductImage.fromId(product.code)?.imageUrl,
                contentDescription = product.name
            )

            Text(
                text = product.name,
                textAlign = TextAlign.Center,
                fontSize = scalableText(16.sp),
                modifier = Modifier
                    .dynamicPadding()
            )

            Text(
                text = product.price.toCurrencyFormat(),
                textAlign = TextAlign.Center,
                fontSize = scalableText(18.sp),
                modifier = Modifier
                    .dynamicPadding()
            )

            ItemButton()
        }
    }
}