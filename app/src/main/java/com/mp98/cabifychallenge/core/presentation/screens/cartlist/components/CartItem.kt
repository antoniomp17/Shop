package com.mp98.cabifychallenge.core.presentation.screens.cartlist.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.mp98.cabifychallenge.core.domain.cart.discount.DiscountType
import com.mp98.cabifychallenge.core.domain.model.Product
import com.mp98.cabifychallenge.core.presentation.models.ProductImage
import com.mp98.cabifychallenge.core.presentation.screens.components.AddOrTakeOutButton
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel
import com.mp98.cabifychallenge.core.utils.dynamicPadding
import com.mp98.cabifychallenge.core.utils.scalableText
import com.mp98.cabifychallenge.core.utils.toCurrencyFormat
import com.mp98.cabifychallenge.ui.theme.primaryColor

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CartItem(
    product: Product,
    productCartViewModel: ProductCartViewModel,
    onSelectProduct: () -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.75f)
            .dynamicPadding(),
        shape = ShapeDefaults.Large,
        onClick = {
            onSelectProduct()
        },
        border = if(product.discount != null) {
            BorderStroke(2.dp, primaryColor)
        } else null,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {

        Column(modifier = Modifier.fillMaxSize().dynamicPadding()){

            if(product.discount != null){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically){

                    Text(
                        text = DiscountType.getDiscountString(product.discount),
                        textAlign = TextAlign.Center,
                        fontSize = scalableText(14.sp),
                        modifier = Modifier.weight(1f),
                        color = primaryColor
                    )

                    Icon(
                        modifier = Modifier.weight(0.25f),
                        imageVector = Icons.Rounded.Info,
                        contentDescription = Icons.Rounded.Info.name,
                        tint = primaryColor
                    )

                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .dynamicPadding()
                    .weight(2f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                GlideImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    model = ProductImage.fromId(product.code)?.imageUrl,
                    contentDescription = product.name,
                    loading = placeholder(
                        CircularProgressDrawable(LocalContext.current).apply { start() }
                    )
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(3f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = product.name,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = scalableText(16.sp)
                    )
                    Text(
                        text = product.price.toCurrencyFormat(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = scalableText(18.sp)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AddOrTakeOutButton(
                    product,
                    productCartViewModel,
                    modifier = Modifier.weight(1f).fillMaxSize(fraction = 0.5f)
                )

                Text(
                    text = productCartViewModel.getTotalOfProduct(product.code).toCurrencyFormat(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    fontSize = scalableText(18.sp)
                )
            }
        }
    }
}