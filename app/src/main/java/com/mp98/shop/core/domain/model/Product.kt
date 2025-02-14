package com.mp98.shop.core.domain.model

data class Product(
    val code: String,
    val name: String,
    val price: Double,
    val discount: String? = null,
    val discountPrice: Double = price*0.95,
    val minQuantity: Int = 3
){

    companion object{
        const val T_SHIRT = "TSHIRT"
        const val VOUCHER = "VOUCHER"
        const val MUG = "MUG"
    }
}