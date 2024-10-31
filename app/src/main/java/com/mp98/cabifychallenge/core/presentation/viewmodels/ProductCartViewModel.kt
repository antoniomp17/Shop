package com.mp98.cabifychallenge.core.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mp98.cabifychallenge.core.domain.cart.Cart
import com.mp98.cabifychallenge.core.domain.cart.discount.DiscountType
import com.mp98.cabifychallenge.core.domain.model.Product
import com.mp98.cabifychallenge.core.domain.usecases.GetProductsUseCase
import com.mp98.cabifychallenge.core.presentation.states.ProductsCartState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductCartViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    var productsCartState by mutableStateOf(ProductsCartState())
        private set

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            val products = getProductsUseCase()
            productsCartState = productsCartState.copy(products = products)
            applyDiscounts(products)
        }
    }

    private fun applyDiscounts(products: List<Product>) {
        val discounts = products.filter { it.discount != null }.map {
            DiscountType.fromDiscount(it)
        }
        productsCartState = productsCartState.copy(cart = Cart(discounts))
    }

    fun addProductToCart(product: Product) {
        productsCartState.cart.addProduct(product)
    }

    fun getTotal(): Double {
        return productsCartState.cart.calculateTotal()
    }
}