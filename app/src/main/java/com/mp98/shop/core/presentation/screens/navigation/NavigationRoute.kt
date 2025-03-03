package com.mp98.shop.core.presentation.screens.navigation

sealed class NavigationRoute(val route: String){
    data object ProductListScreen: NavigationRoute("ProductListScreen")
    data object CartListScreen: NavigationRoute("CartListScreen")
    data object PaymentScreen: NavigationRoute("PaymentScreen")
}