package com.mp98.cabifychallenge.core.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mp98.cabifychallenge.core.domain.usecases.GetProductsUseCase
import com.mp98.cabifychallenge.core.presentation.states.ProductListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    var state by mutableStateOf(ProductListState())
        private set

    init{
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            state = state.copy(products = getProductsUseCase())
        }
    }
}