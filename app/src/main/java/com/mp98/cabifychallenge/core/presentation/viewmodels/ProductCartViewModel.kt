package com.mp98.cabifychallenge.core.presentation.viewmodels

import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mp98.cabifychallenge.core.domain.cart.discount.DiscountType
import com.mp98.cabifychallenge.core.domain.model.Product
import com.mp98.cabifychallenge.core.domain.model.ProductCart
import com.mp98.cabifychallenge.core.domain.usecases.GetAllCartProductsUseCase
import com.mp98.cabifychallenge.core.domain.usecases.GetProductsUseCase
import com.mp98.cabifychallenge.core.domain.usecases.RemoveCartProductUseCase
import com.mp98.cabifychallenge.core.domain.usecases.SetCartProductUseCase
import com.mp98.cabifychallenge.core.presentation.screens.navigation.NavigationRoute
import com.mp98.cabifychallenge.core.presentation.states.ProductsCartState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ProductCartViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getAllCartProductsUseCase: GetAllCartProductsUseCase,
    private val setCartProductUseCase: SetCartProductUseCase,
    private val removeCartProductUseCase: RemoveCartProductUseCase,
) : ViewModel() {

    private val _productsCartState = MutableStateFlow(ProductsCartState())
    val productsCartState: StateFlow<ProductsCartState> get() = _productsCartState

    private var _voiceSearchLauncher: ActivityResultLauncher<Intent>? = null

    init {
        fetchProducts()
    }

    fun fetchProducts() {
        viewModelScope.launch {
            try {
                val products = getProductsUseCase()
                _productsCartState.update {
                    state -> state.copy(
                        products = products,
                        filteredProducts = products
                    )
                }

                applyDiscounts()
                fetchCartProducts()

            } catch (e: Exception) {
                //TODO: Manejar el error en el estado
                //_productsCartState.update { state -> state.copy(error = "Error al cargar productos") }
            }
        }
    }

    fun fetchCartProducts() {
        viewModelScope.launch {
            try {
                getAllCartProductsUseCase().collectLatest { cartProducts ->
                    if(_productsCartState.value.products.isNotEmpty()){
                        _productsCartState.update { state ->
                            state.copy(
                                cart = state.cart.setItems(cartProducts, state.products)
                            )
                        }
                    }
                }
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
        viewModelScope.launch {
            try {
                setCartProductUseCase(ProductCart(code = product.code))
            } catch (e: Exception) {
                //TODO: Manejar el error en el estado
                //_productsCartState.update { state -> state.copy(error = "Error al cargar productos") }
            }
        }
    }

    fun removeProductToCart(product: Product) {
        viewModelScope.launch {
            try {
                removeCartProductUseCase(product.code)
            } catch (e: Exception) {
                //TODO: Manejar el error en el estado
                //_productsCartState.update { state -> state.copy(error = "Error al cargar productos") }
            }
        }
    }

    fun getProductsOfCode(code: String): List<Product> {
        return productsCartState.value.cart.getProductsOfCode(code)
    }

    fun changeShowDiscountDialog(discountType: String?){
        _productsCartState.update { state ->
            state.copy(showDiscountDialog = discountType)
        }
    }

    fun onSearchTextChanged(newText: String) {
        _productsCartState.update { state ->
            state.copy(searchText = newText)
        }
    }

    fun searchProducts() {
        val query = _productsCartState.value.searchText
        _productsCartState.update { state ->
            state.copy(
                filteredProducts = state.products.filter {
                    it.name.contains(query, ignoreCase = true) ||
                            it.code.contains(query, ignoreCase = true)
                }
            )
        }
    }

    fun getTotalOfProduct(code: String): Double {
        return productsCartState.value.cart.getTotalOfProduct(code)
    }

    fun getTotalWithDiscount(): Double {
        return productsCartState.value.cart.calculateTotalWithDiscount()
    }

    fun onVoiceSearchResult(text: String) {
        onSearchTextChanged(text)
        searchProducts()
    }

    fun setVoiceSearchLauncher(launcher: ActivityResultLauncher<Intent>) {
        _voiceSearchLauncher = launcher
    }

    fun launchVoiceSearch() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to search")
        }
        _voiceSearchLauncher?.launch(intent)
    }

    fun changeScreen(screen: NavigationRoute){
        _productsCartState.update { state ->
            state.copy(screen = screen)
        }
    }
}