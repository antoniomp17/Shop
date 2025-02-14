package com.mp98.shop.core.presentation.screens.customscaffold.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.mp98.shop.R
import com.mp98.shop.core.presentation.viewmodels.ProductCartViewModel
import com.mp98.shop.core.utils.scalableText

@Composable
fun GetCredentialsBottomBar(productCartViewModel: ProductCartViewModel){

    val state by productCartViewModel.productsCartState.collectAsState()

    BottomAppBar{
        Row (modifier = Modifier
            .testTag(tag = "GetCredentialsBottomBar")
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically){

            Button(
                onClick = {
                    //productCartViewModel.getCredentials()
                },
                modifier = Modifier
                    .fillMaxHeight(fraction = 0.7f)
                    .fillMaxWidth(fraction = 0.8f)
            ){
                Text(
                    text = stringResource(id = R.string.identy_credential_question),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}