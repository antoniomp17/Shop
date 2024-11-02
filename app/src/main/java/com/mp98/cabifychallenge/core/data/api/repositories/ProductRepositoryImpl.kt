package com.mp98.cabifychallenge.core.data.api.repositories

import com.mp98.cabifychallenge.core.data.api.mapper.toDomain
import com.mp98.cabifychallenge.core.data.api.service.ProductService
import com.mp98.cabifychallenge.core.data.database.mapper.toDomain
import com.mp98.cabifychallenge.core.domain.cart.discount.DiscountType
import com.mp98.cabifychallenge.core.domain.model.Product
import com.mp98.cabifychallenge.core.domain.repositories.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService
) : ProductRepository {

    override suspend fun getProducts(): List<Product> {
        val productList = productService.getProducts().products.map { it.toDomain() }
        return modifyDiscountsForCabifyChallenge(productList)
    }

    private fun modifyDiscountsForCabifyChallenge(productList: List<Product>): List<Product>{
        val productsWithDiscount = productList.map { product ->
            when (product.code) {
                Product.VOUCHER-> {
                    product.copy(discount = DiscountType.TWO_FOR_ONE_DISCOUNT)
                }
                Product.T_SHIRT -> {
                    product.copy(
                        discount = DiscountType.BULK_DISCOUNT,
                        discountPrice = 19.0,
                        minQuantity = 3
                    )
                }
                else -> {
                    product
                }
            }
        }
        return productsWithDiscount
    }
}