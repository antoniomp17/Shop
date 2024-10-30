package com.mp98.cabifychallenge.core.presentation.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mp98.cabifychallenge.core.presentation.screens.productlist.ProductList

@Composable
fun NavigationHost(
    navHostController: NavHostController
){

    NavHost(navController = navHostController, startDestination = NavigationRoute.ProductListScreen.route){
        composable(NavigationRoute.ProductListScreen.route) {
            ProductList()
        }
    }

}