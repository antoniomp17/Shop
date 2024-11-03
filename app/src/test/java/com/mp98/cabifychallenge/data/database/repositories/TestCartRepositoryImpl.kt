package com.mp98.cabifychallenge.data.database.repositories

import com.mp98.cabifychallenge.core.data.database.bbdd.AppDao
import com.mp98.cabifychallenge.core.data.database.mapper.toDomain
import com.mp98.cabifychallenge.core.data.database.mapper.toEntity
import com.mp98.cabifychallenge.core.data.database.model.ProductCartEntity
import com.mp98.cabifychallenge.core.data.database.repositories.CartRepositoryImpl
import com.mp98.cabifychallenge.core.domain.model.ProductCart
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class TestCartRepositoryImpl {

    private lateinit var cartRepository: CartRepositoryImpl
    private val appDao: AppDao = mock()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        cartRepository = CartRepositoryImpl(appDao)
    }

    @Test
    fun `getAllProducts returns mapped ProductCart list`() = runTest {
        val databaseProducts = listOf(
            ProductCartEntity(code = "TSHIRT"),
            ProductCartEntity(code = "MUG")
        )
        val expectedProducts = databaseProducts.map { it.toDomain() }

        whenever(appDao.getAllProducts()).thenReturn(flowOf(databaseProducts))

        val result = cartRepository.getAllProducts().toList().flatten()

        assertThat(result, equalTo(expectedProducts))
    }

    @Test
    fun `insertProduct calls dao insertProduct with correct entity`() = runBlocking {
        val product = ProductCart(code = "VOUCHER")
        val expectedEntity = product.toEntity()

        cartRepository.insertProduct(product)

        verify(appDao, times(1)).insertProduct(expectedEntity)
    }

    @Test
    fun `removeProduct calls dao removeProduct with correct code`() = runBlocking {
        val productCode = "VOUCHER"

        cartRepository.removeProduct(productCode)

        verify(appDao, times(1)).removeProduct(productCode)
    }
}