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

@Composable
fun ShopMainContent(productCartViewModel: ProductCartViewModel = hiltViewModel()) {
    val state by productCartViewModel.productsCartState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(state.sessionUrl) {
        state.sessionUrl?.let { url ->
            val deepLinkUri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, deepLinkUri).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)

            } else {
                Log.e("DeepLink", "No app found to handle the deep link")
            }
        }
    }

    ShopTheme{

        val navHostController = rememberNavController()

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
            }
        )
    }
}