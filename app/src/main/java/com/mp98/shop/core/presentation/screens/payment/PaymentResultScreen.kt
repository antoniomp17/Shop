package com.mp98.shop.core.presentation.screens.payment

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mp98.shop.core.presentation.screens.navigation.NavigationRoute
import com.mp98.shop.core.presentation.viewmodels.ProductCartViewModel
import com.mp98.shop.core.utils.dynamicPadding

@Composable
fun PaymentResultScreen(
    productCartViewModel: ProductCartViewModel,
    onChangeScreen: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .dynamicPadding(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = "Compra exitosa",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "¡Compra realizada con éxito!",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = {
                productCartViewModel.changeScreen(NavigationRoute.ProductListScreen)
                onChangeScreen()
            }) {
                Text(text = "Volver a la tienda")
            }
        }
    }
}