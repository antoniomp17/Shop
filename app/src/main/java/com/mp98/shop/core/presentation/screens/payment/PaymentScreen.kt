package com.mp98.shop.core.presentation.screens.payment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mp98.shop.core.presentation.viewmodels.ProductCartViewModel
import com.mp98.shop.core.utils.toCurrencyFormat

@Composable
fun PaymentScreen(cartViewModel: ProductCartViewModel) {

    val state by cartViewModel.productsCartState.collectAsState()

    var selectedMethod by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Button(
            onClick = { selectedMethod = "login" },
            colors = ButtonDefaults.buttonColors(
                if (selectedMethod == "login") MaterialTheme.colorScheme.secondary
                else MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar sesión con cuenta")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                selectedMethod = "identy"
                cartViewModel.initVerifierSession()
            },
            colors = ButtonDefaults.buttonColors(
                if (selectedMethod == "identy") MaterialTheme.colorScheme.secondary
                else MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Usar credencial de Identy")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Resumen del pedido", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))

                state.products.forEach { product ->
                    if (cartViewModel.getProductsOfCode(product.code).isNotEmpty()){
                        Text("${product.name} - " +
                                cartViewModel.getProductsOfCode(product.code).size.toString()
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text("Total: ${state.cart.total.toCurrencyFormat()}", fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                cartViewModel.initVerifierSession()
            },
            enabled = selectedMethod != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continuar con el pago")
        }
    }
}