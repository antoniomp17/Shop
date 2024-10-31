package com.mp98.cabifychallenge.core.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.mp98.cabifychallenge.core.domain.cart.discount.BulkDiscount
import com.mp98.cabifychallenge.core.domain.cart.Cart
import com.mp98.cabifychallenge.core.domain.cart.discount.TwoForOneDiscount
import com.mp98.cabifychallenge.core.domain.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CartViewModel : ViewModel() {

    private val discounts = listOf(
        TwoForOneDiscount("VOUCHER", 5.0),
        BulkDiscount("TSHIRT", 3, 19.0, 20.0)
    )

    private val cart = Cart(discounts)
    private val _total = MutableStateFlow(0.0)
    val total: StateFlow<Double> get() = _total

    fun addProduct(product: Product) {
        cart.addProduct(product)
        _total.value = cart.calculateTotal()
    }

}