package com.mp98.cabifychallenge.core.domain.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mp98.cabifychallenge.R
import com.mp98.cabifychallenge.core.domain.cart.discount.DiscountType

data class Product(
    val code: String,
    val name: String,
    val price: Double,
    val discount: String? = null,
    val discountPrice: Double = price,
    val minQuantity: Int = 0
){

    companion object{
        const val T_SHIRT = "TSHIRT"
        const val VOUCHER = "VOUCHER"
        const val MUG = "MUG"
    }
}