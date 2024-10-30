package com.mp98.cabifychallenge.core.presentation.screens.productlist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mp98.cabifychallenge.R
import com.mp98.cabifychallenge.core.domain.model.Product
import com.mp98.cabifychallenge.core.presentation.models.ProductImage
import com.mp98.cabifychallenge.core.utils.toCurrencyFormat

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductItem(
    product: Product,
    onSelectProduct: () -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .padding(4.dp),
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
                    .padding(top = 16.dp, end = 8.dp, start = 8.dp, bottom = 8.dp)
                    .size(128.dp),
                model = ProductImage.fromId(product.code)?.imageUrl,
                contentDescription = product.name
            )

            Text(
                text = product.name,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(8.dp)
            )

            Text(
                text = product.price.toCurrencyFormat(),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(8.dp)
            )

            ItemButton()
        }
    }
}