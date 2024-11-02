package com.mp98.cabifychallenge

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mp98.cabifychallenge.core.presentation.screens.customscaffold.CustomScaffold
import com.mp98.cabifychallenge.core.presentation.screens.navigation.NavigationHost
import com.mp98.cabifychallenge.core.presentation.screens.navigation.NavigationRoute
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel
import com.mp98.cabifychallenge.ui.theme.CabifyChallengeTheme

@Composable
fun CabifyChallengeMainContent(productCartViewModel: ProductCartViewModel = hiltViewModel()){
    CabifyChallengeTheme{

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