package com.mp98.shop.core.domain.cart.discount

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mp98.shop.R
import com.mp98.shop.core.domain.model.Product

sealed class DiscountType{
    companion object {
        const val TWO_FOR_ONE_DISCOUNT = "2X1"
        const val BULK_DISCOUNT = "BULK"

        fun fromDiscount(product: Product): Discount? {
            return when (product.discount) {
                TWO_FOR_ONE_DISCOUNT -> TwoForOneDiscount(product)
                BULK_DISCOUNT -> BulkDiscount(product)
                else -> null
            }
        }

        @Composable
        fun getDiscountString(discount: String?): String {
            return when(discount){
                TWO_FOR_ONE_DISCOUNT -> stringResource(R.string.discount2x1)
                BULK_DISCOUNT -> stringResource(R.string.discountBulk)
                else -> ""
            }
        }

        @Composable
        fun getDiscountDescriptionString(discount: String?): String {
            return when(discount){
                TWO_FOR_ONE_DISCOUNT -> stringResource(R.string.discount2x1Description)
                BULK_DISCOUNT -> stringResource(R.string.discountBulkDescription)
                else -> ""
            }
        }
    }
}