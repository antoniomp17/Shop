package com.mp98.shop

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.mp98.shop.core.presentation.screens.customscaffold.CustomScaffold
import com.mp98.shop.core.presentation.screens.navigation.NavigationHost
import com.mp98.shop.core.presentation.screens.navigation.NavigationRoute
import com.mp98.shop.core.presentation.viewmodels.ProductCartViewModel
import com.mp98.shop.ui.theme.ShopTheme
import org.json.JSONObject

@Composable
fun ShopMainContent(
    productCartViewModel: ProductCartViewModel = hiltViewModel(),
    activity: MainActivity
) {

    val credentialsResponse = activity.intent.getStringArrayListExtra("credentialsResponse")

    ShopTheme{

        val navHostController = rememberNavController()

        LaunchedEffect(credentialsResponse) {
            credentialsResponse?.let { credentials ->
                val jsonCredentials = credentials.mapNotNull {
                    try {
                        JSONObject(it)
                    } catch (e: Exception) {
                        null
                    }
                }
                productCartViewModel.setSessionState(jsonCredentials)
                productCartViewModel.changeScreen(NavigationRoute.PaymentScreen)
                navHostController.navigate(NavigationRoute.PaymentScreen.route)
            }
        }

        CustomScaffold(
            modifier = Modifier
                .fillMaxSize(),
            productCartViewModel =  productCartViewModel,
            content = {
                NavigationHost(
                    navHostController = navHostController,
                    productCartViewModel = productCartViewModel)
            },
            onChangeToCart = {
                navHostController.navigate(NavigationRoute.CartListScreen.route)
            },
            onChangeToProducts = {
                navHostController.navigate(NavigationRoute.ProductListScreen.route)
            },
            onChangeToPayment = {
                navHostController.navigate(NavigationRoute.PaymentScreen.route)
            }
        )
    }
}