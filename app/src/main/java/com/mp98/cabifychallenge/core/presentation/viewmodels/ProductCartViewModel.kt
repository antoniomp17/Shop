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
                _productsCartState.update { state -> state.copy(products = products) }
                applyDiscounts(products)
            } catch (e: Exception) {
                // Manejar el error en el estado
                //_productsCartState.update { state -> state.copy(error = "Error al cargar productos") }
            }
        }
    }

    private fun applyDiscounts(products: List<Product>) {
        val discounts = products.filter { it.discount != null }.map {
            DiscountType.fromDiscount(it)
        }
        _productsCartState.update { state -> state.copy(cart = Cart(discounts)) }
    }

    fun addProductToCart(product: Product) {
        _productsCartState.update { state ->
            val updatedCart = state.cart.addProduct(product)
            state.copy(cart = updatedCart)
        }
    }

    fun getTotal(): Double {
        return _productsCartState.value.cart.calculateTotal()
    }
}