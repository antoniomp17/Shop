package com.mp98.shop.data.api.mapper

import com.mp98.shop.core.data.api.mapper.toDomain
import com.mp98.shop.core.data.api.mapper.toEntity
import com.mp98.shop.core.data.api.model.ProductEntity
import com.mp98.shop.core.domain.model.Product
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class TestProductMapper {

    @Test
    fun `test mapping from ProductEntity to Product`() {
        val entity = ProductEntity(
            code = "VOUCHER",
            name = "Test Product",
            price = 10.0,
            discount = "2x1",
            discountPrice = 0.0,
            minQuantity = 0
        )

        val domain = entity.toDomain()

        assertThat(domain.code, `is`(equalTo(entity.code)))
        assertThat(domain.name, `is`(equalTo(entity.name)))
        assertThat(domain.price, `is`(equalTo(entity.price)))
        assertThat(domain.discount, `is`(equalTo(entity.discount)))
        assertThat(domain.discountPrice, `is`(equalTo(entity.discountPrice)))
    }

    @Test
    fun `test mapping from Product to ProductEntity`() {
        val entity = Product(
            code = "VOUCHER",
            name = "Test Product",
            price = 10.0,
            discount = "2x1",
            discountPrice = 0.0,
            minQuantity = 0
        )

        val domain = entity.toEntity()

        assertThat(domain.code, `is`(equalTo(entity.code)))
        assertThat(domain.name, `is`(equalTo(entity.name)))
        assertThat(domain.price, `is`(equalTo(entity.price)))
        assertThat(domain.discount, `is`(equalTo(entity.discount)))
        assertThat(domain.discountPrice, `is`(equalTo(entity.discountPrice)))

    }
}