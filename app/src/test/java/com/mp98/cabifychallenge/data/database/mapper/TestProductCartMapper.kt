package com.mp98.cabifychallenge.data.database.mapper

import com.mp98.cabifychallenge.core.data.database.mapper.toDomain
import com.mp98.cabifychallenge.core.data.database.mapper.toEntity
import com.mp98.cabifychallenge.core.data.database.model.ProductCartEntity
import com.mp98.cabifychallenge.core.domain.model.ProductCart
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class TestProductMapperTest {
    @Test
    fun `test mapping from ProductCartEntity to ProductCart`() {
        val entity = ProductCartEntity(
            code = "VOUCHER"
        )

        val domain = entity.toDomain()

        assertThat(domain.code, `is`(equalTo(entity.code)))
    }

    @Test
    fun `test mapping from ProductCart to ProductCartEntity`() {
        val entity = ProductCart(
            code = "VOUCHER"
        )

        val domain = entity.toEntity()

        assertThat(domain.code, `is`(equalTo(entity.code)))
    }
}