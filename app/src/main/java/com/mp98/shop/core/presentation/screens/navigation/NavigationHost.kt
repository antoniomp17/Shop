package com.mp98.shop.core.presentation.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mp98.shop.core.presentation.screens.cartlist.CartList
import com.mp98.shop.core.presentation.screens.payment.PaymentResultScreen
import com.mp98.shop.core.presentation.screens.payment.PaymentScreen
import com.mp98.shop.core.presentation.screens.productlist.ProductList
import com.mp98.shop.core.presentation.viewmodels.ProductCartViewModel

@Composable
fun NavigationHost(
    navHostController: NavHostController,
    productCartViewModel: ProductCartViewModel
){

    NavHost(navController = navHostController, startDestination = NavigationRoute.ProductListScreen.route){
        composable(NavigationRoute.ProductListScreen.route) {
            ProductList(productCartViewModel = productCartViewModel)
        }
        composable(NavigationRoute.CartListScreen.route) {
            CartList(productCartViewModel = productCartViewModel)
        }
        composable(NavigationRoute.PaymentScreen.route) {
            PaymentScreen(cartViewModel = productCartViewModel){
                navHostController.navigate(NavigationRoute.PaymentResultScreen.route)
            }
        }
        composable(NavigationRoute.PaymentResultScreen.route) {
            PaymentResultScreen(productCartViewModel = productCartViewModel){
                navHostController.navigate(NavigationRoute.ProductListScreen.route)
            }
        }
    }

}