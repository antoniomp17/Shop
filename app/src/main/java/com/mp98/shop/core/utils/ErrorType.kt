package com.mp98.shop.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mp98.shop.R

object ErrorType {
    const val ERROR_FETCHING_PRODUCTS = "ERROR_FETCHING_PRODUCTS"
    const val ERROR_FETCHING_CART_PRODUCTS = "ERROR_FETCHING_CART_PRODUCTS"
    const val ERROR_ADDING_PRODUCT_TO_CART = "ERROR_ADDING_PRODUCT_TO_CART"
    const val ERROR_REMOVING_PRODUCT_FROM_CART = "ERROR_REMOVING_PRODUCT_FROM_CART"

    @Composable
    fun getErrorTypeMessage(errorType: String): String {
        return when (errorType) {
            "ERROR_FETCHING_PRODUCTS" -> stringResource(R.string.error_fetching_products)
            "ERROR_FETCHING_CART_PRODUCTS" -> stringResource(R.string.error_fetching_cart_products)
            "ERROR_ADDING_PRODUCT_TO_CART" -> stringResource(R.string.error_adding_product_to_cart)
            "ERROR_REMOVING_PRODUCT_FROM_CART" -> stringResource(R.string.error_removing_product_from_cart)
            else -> stringResource(R.string.error_unknown)
        }
    }
}