package com.mp98.cabifychallenge.core.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mp98.cabifychallenge.core.domain.cart.Cart
import com.mp98.cabifychallenge.core.domain.cart.discount.DiscountType
import com.mp98.cabifychallenge.core.domain.model.Product
import com.mp98.cabifychallenge.core.domain.usecases.GetProductsUseCase
import com.mp98.cabifychallenge.core.presentation.states.ProductsCartState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductCartViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _productsCartState = MutableStateFlow(ProductsCartState())
    val productsCartState: StateFlow<ProductsCartState> get() = _productsCartState

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                val products = getProductsUseCase()
                _productsCartState.update {
                    state -> state.copy(products = products)
                }

                modifyDiscountsForCabifyChallenge()

                applyDiscounts()

            } catch (e: Exception) {
                //TODO: Manejar el error en el estado
                //_productsCartState.update { state -> state.copy(error = "Error al cargar productos") }
            }
        }
    }

    private fun applyDiscounts() {
        val discounts =  productsCartState.value.products.filter { it.discount != null }.map {
            DiscountType.fromDiscount(it)
        }
        _productsCartState.update { state ->
            state.copy(cart = state.cart.setDiscounts(discounts))
        }
    }

    fun addProductToCart(product: Product) {
        _productsCartState.update { state ->
            val updatedCart = state.cart.addProduct(product)
            state.copy(cart = updatedCart)
        }
    }

    fun removeProductToCart(product: Product) {
        _productsCartState.update { state ->
            val updatedCart = state.cart.removeProduct(product)
            state.copy(cart = updatedCart)
        }
    }

    private fun modifyDiscountsForCabifyChallenge(){

        val productsWithDiscount = productsCartState.value.products.map { product ->
            when (product.code) {
                "VOUCHER" -> {
                    product.copy(discount = "2X1")
                }
                "TSHIRT" -> {
                    product.copy(
                        discount = "BULK",
                        discountPrice = 19.0,
                        minQuantity = 3
                    )
                }
                else -> {
                    product
                }
            }
        }

        _productsCartState.update { state ->
            state.copy(products = productsWithDiscount)
        }
    }
}