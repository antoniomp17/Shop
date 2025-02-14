package com.mp98.shop.data.api.repositories

import com.mp98.shop.core.data.api.model.ProductEntity
import com.mp98.shop.core.data.api.repositories.ProductRepositoryImpl
import com.mp98.shop.core.data.api.service.ProductResponse
import com.mp98.shop.core.data.api.service.ProductService
import com.mp98.shop.core.domain.cart.discount.DiscountType
import com.mp98.shop.core.domain.model.Product
import com.mp98.shop.core.domain.repositories.ProductRepository
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.mockito.Mockito.`when`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class TestProductRepositoryImpl {

    @Mock
    private lateinit var productService: ProductService

    private lateinit var productRepository: ProductRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        productRepository = ProductRepositoryImpl(productService)
    }

    @Test
    fun `test getProducts returns mapped Products list`() = runTest {
        val productResponse = ProductResponse(
            products = listOf(
                ProductEntity(
                    code = "VOUCHER",
                    name = "Test Product",
                    price = 10.0,
                    discount = "2X1",
                    discountPrice = 0.0,
                    minQuantity = 0
                )
            )
        )

        `when`(productService.getProducts()).thenReturn(productResponse)

        val result = productRepository.getProducts()

        val expected = listOf(
            Product(
                code = "VOUCHER",
                name = "Test Product",
                price = 10.0,
                discount = "2X1",
                discountPrice = 0.0,
                minQuantity = 0
            )
        )

        assertThat(result, `is`(equalTo(expected)))
    }

    @Test
    fun `getProducts returns empty list when productService returns empty list`() = runTest {
        val emptyProductResponse = ProductResponse(products = emptyList())
        `when`(productService.getProducts()).thenReturn(emptyProductResponse)

        val result = productRepository.getProducts()

        assertTrue(result.isEmpty())
    }

    @Test
    fun `getProducts returns products without modifying discount for unknown codes`() = runTest {
        val productWithoutDiscount = ProductEntity(
            code = "OTHER_CODE",
            name = "Other Product",
            price = 15.0,
            discount = null,
            discountPrice = 0.0,
            minQuantity = 0
        )
        val productResponse = ProductResponse(products = listOf(productWithoutDiscount))
        `when`(productService.getProducts()).thenReturn(productResponse)

        val result = productRepository.getProducts()

        assertThat(result[0].discount, `is`(nullValue()))
    }

    @Ignore("Only for Shop Challenge")
    @Test
    fun `getProducts applies 2x1 discount to VOUCHER products`() = runTest {
        val voucherProduct = ProductEntity(
            code = Product.VOUCHER,
            name = "Voucher",
            price = 5.0,
            discount = null,
            discountPrice = 0.0,
            minQuantity = 0
        )
        val productResponse = ProductResponse(products = listOf(voucherProduct))
        `when`(productService.getProducts()).thenReturn(productResponse)

        val result = productRepository.getProducts()

        assertThat(result[0].discount, `is`(equalTo(DiscountType.TWO_FOR_ONE_DISCOUNT)))
    }

    @Ignore("Only for Shop Challenge")
    @Test
    fun `getProducts applies bulk discount to T_SHIRT products`() = runTest {
        val tshirtProduct = ProductEntity(
            code = Product.T_SHIRT,
            name = "T-Shirt",
            price = 20.0,
            discount = null,
            discountPrice = 0.0,
            minQuantity = 0
        )
        val productResponse = ProductResponse(products = listOf(tshirtProduct))
        `when`(productService.getProducts()).thenReturn(productResponse)

        val result = productRepository.getProducts()

        assertThat(result[0].discount, `is`(equalTo(DiscountType.BULK_DISCOUNT)))
        assertThat(result[0].discountPrice, `is`(equalTo(19.0)))
        assertThat(result[0].minQuantity, `is`(equalTo(3)))
    }

    @Ignore("Only for Shop Challenge")
    @Test
    fun `getProducts applies discounts correctly to relevant products`() = runTest {
        val voucherProduct = ProductEntity(
            code = Product.VOUCHER,
            name = "Voucher",
            price = 5.0,
            discount = null,
            discountPrice = 0.0,
            minQuantity = 0
        )
        val tshirtProduct = ProductEntity(
            code = Product.T_SHIRT,
            name = "T-Shirt",
            price = 20.0,
            discount = null,
            discountPrice = 0.0,
            minQuantity = 0
        )
        val otherProduct = ProductEntity(
            code = "OTHER_CODE",
            name = "Other Product",
            price = 15.0,
            discount = null,
            discountPrice = 0.0,
            minQuantity = 0
        )
        val productResponse = ProductResponse(products = listOf(voucherProduct, tshirtProduct, otherProduct))
        `when`(productService.getProducts()).thenReturn(productResponse)

        val result = productRepository.getProducts()

        assertThat(result[0].discount, `is`(equalTo(DiscountType.TWO_FOR_ONE_DISCOUNT)))
        assertThat(result[1].discount, `is`(equalTo(DiscountType.BULK_DISCOUNT)))
        assertThat(result[1].discountPrice, `is`(equalTo(19.0)))
        assertThat(result[1].minQuantity, `is`(equalTo(3)))
        assertThat(result[2].discount, `is`(nullValue()))
    }
}