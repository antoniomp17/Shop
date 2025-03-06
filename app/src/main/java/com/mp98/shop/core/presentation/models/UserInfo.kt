package com.mp98.shop.core.presentation.models

data class UserInfo(
    val name: String,
    val lastName: String,
    val email: String,
    val address: String,
    val cardNumber: String,
    val cardHolder: String,
    val cardExpiration: String = "01/25"
)