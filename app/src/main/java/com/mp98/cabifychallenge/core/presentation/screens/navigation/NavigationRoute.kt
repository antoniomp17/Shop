package com.mp98.cabifychallenge.core.presentation.screens.navigation

sealed class NavigationRoute(val route: String){
    data object ProductListScreen: NavigationRoute("ProductListScreen")
    data object CartListScreen: NavigationRoute("CartListScreen")
}