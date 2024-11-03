package com.mp98.cabifychallenge.presentation.viewmodels

import com.mp98.cabifychallenge.core.domain.model.Product
import com.mp98.cabifychallenge.core.domain.model.ProductCart
import com.mp98.cabifychallenge.core.domain.usecases.GetAllCartProductsUseCase
import com.mp98.cabifychallenge.core.domain.usecases.GetProductsUseCase
import com.mp98.cabifychallenge.core.domain.usecases.RemoveCartProductUseCase
import com.mp98.cabifychallenge.core.domain.usecases.SetCartProductUseCase
import com.mp98.cabifychallenge.core.presentation.viewmodels.ProductCartViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.flow.flow
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

//TODO: Corregir tests

class TestProductCartViewModel {
    private lateinit var viewModel: ProductCartViewModel
    private lateinit var getProductsUseCase: GetProductsUseCase
    private lateinit var getAllCartProductsUseCase: GetAllCartProductsUseCase
    private lateinit var setCartProductUseCase: SetCartProductUseCase
    private lateinit var removeCartProductUseCase: RemoveCartProductUseCase

    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        getProductsUseCase = mock(GetProductsUseCase::class.java)
        getAllCartProductsUseCase = mock(GetAllCartProductsUseCase::class.java)
        setCartProductUseCase = mock(SetCartProductUseCase::class.java)
        removeCartProductUseCase = mock(RemoveCartProductUseCase::class.java)

        viewModel = ProductCartViewModel(
            getProductsUseCase,
            getAllCartProductsUseCase,
            setCartProductUseCase,
            removeCartProductUseCase
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchProducts updates state with products`() = runTest {
        val mockProducts = listOf(Product("1", "T-Shirt", 20.0, null))
        `when`(getProductsUseCase()).thenReturn(mockProducts)

        viewModel.fetchProducts()
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(mockProducts, viewModel.productsCartState.value.products)
    }

    @Test
    fun `addProductToCart calls setCartProductUseCase`() = runTest {
        val product = Product("1", "T-Shirt", 20.0, null)

        viewModel.addProductToCart(product)
        testDispatcher.scheduler.advanceUntilIdle()

        verify(setCartProductUseCase).invoke(ProductCart(code = product.code))
    }

    @Test
    fun `removeProductToCart calls removeCartProductUseCase`() = runTest {
        val product = Product("1", "T-Shirt", 20.0, null)

        viewModel.removeProductToCart(product)
        testDispatcher.scheduler.advanceUntilIdle()

        verify(removeCartProductUseCase).invoke(product.code)
    }

}