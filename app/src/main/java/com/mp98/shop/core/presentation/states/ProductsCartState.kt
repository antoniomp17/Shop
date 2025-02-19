package com.mp98.shop.core.presentation.states

import com.mp98.shop.core.domain.cart.Cart
import com.mp98.shop.core.domain.model.Product
import com.mp98.shop.core.presentation.screens.navigation.NavigationRoute
import org.json.JSONObject

data class ProductsCartState(

    val products: List<Product> = emptyList(),
    val cart: Cart = Cart(discounts = emptyList()),
    val total: Double = 0.0,

    val showDiscountDialog: String? = null,
    val searchText: String = "",
    val filteredProducts: List<Product> = emptyList(),
    val screen: NavigationRoute = NavigationRoute.ProductListScreen,

    val name: String = "",

    val sessionUrl: String? = null,
    val sessionState: JSONObject? = null,

    val error: String? = null

)