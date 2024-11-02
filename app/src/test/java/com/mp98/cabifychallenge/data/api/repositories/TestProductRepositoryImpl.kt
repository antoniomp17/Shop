package com.mp98.cabifychallenge.data.api.repositories

import com.mp98.cabifychallenge.core.data.api.repositories.ProductRepositoryImpl
import com.mp98.cabifychallenge.core.data.api.service.ProductService
import com.mp98.cabifychallenge.core.domain.model.Product
import com.mp98.cabifychallenge.core.domain.repositories.ProductRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.`is`
import org.mockito.Mockito.`when`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ConsumeRepositoryImplTest {

    @Mock
    private lateinit var productService: ProductService

    private lateinit var productRepository: ProductRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        productRepository = ProductRepositoryImpl(productService)
    }

    //TODO: REVISAR
    @Test
    fun `test getConsumeInfo returns mapped ConsumeInfo list`() = runTest {
        val productEntities = listOf(
            Product(
                code = "VOUCHER",
                name = "Test Product",
                price = 10.0,
                discount = "2x1",
                discountPrice = 0.0,
                minQuantity = 0
            )
        )

        `when`(productRepository.getProducts()).thenReturn(productEntities)

        val result = productRepository.getProducts()

        val expected = listOf(
            listOf(
                Product(
                    code = "VOUCHER",
                    name = "Test Product",
                    price = 10.0,
                    discount = "2x1",
                    discountPrice = 0.0,
                    minQuantity = 0
                )
            )
        )

        assertThat(result, `is`(equalTo(expected)))
    }
}