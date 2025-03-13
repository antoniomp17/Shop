package com.mp98.shop.core.presentation.screens.payment

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mp98.shop.core.presentation.screens.navigation.NavigationRoute
import com.mp98.shop.core.presentation.viewmodels.ProductCartViewModel
import com.mp98.shop.core.utils.toCurrencyFormat

@Composable
fun PaymentScreen(cartViewModel: ProductCartViewModel, onChangeScreen: () -> Unit) {

    val state by cartViewModel.productsCartState.collectAsState()
    var selectedMethod by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Button(
            onClick = { selectedMethod = "login" },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar sesión con cuenta")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {

                selectedMethod = "identy"

                val requestedCredentials = arrayListOf("DocumentId", "ContactCredential", "BankCredential")

                val intent = Intent("io.identywallet.RECEIVE_VC_REQUEST").apply {
                    putStringArrayListExtra("requestedCredentials", requestedCredentials)
                    addCategory(Intent.CATEGORY_DEFAULT)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }

                if (intent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(intent)
                } else {
                    Log.e("VCRequest", "No app found to handle the VC request")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Usar credencial de Identy")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Resumen del pedido
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
                    if (cartViewModel.getProductsOfCode(product.code).isNotEmpty()) {
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

        // Información del usuario
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Información del usuario", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))

                Text("Nombre: ${state.userInfo?.name ?: "No disponible"}")
                Text("Apellido: ${state.userInfo?.lastName ?: "No disponible"}")
                Text("Email: ${state.userInfo?.email ?: "No disponible"}")
                Text("Dirección: ${state.userInfo?.address ?: "No disponible"}")

                Spacer(modifier = Modifier.height(8.dp))

                Text("Tarjeta de crédito", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Número: ${state.userInfo?.cardNumber ?: "No disponible"}")
                Text("Titular: ${state.userInfo?.cardHolder ?: "No disponible"}")
                Text("Expiración: ${state.userInfo?.cardExpiration ?: "No disponible"}")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                cartViewModel.changeScreen(NavigationRoute.PaymentResultScreen)
                onChangeScreen()
            },
            enabled = state.userInfo != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Realizar pago")
        }
    }
}