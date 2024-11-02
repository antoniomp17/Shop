package com.mp98.cabifychallenge.core.data.database.repositories

import com.mp98.cabifychallenge.core.data.database.bbdd.AppDao
import com.mp98.cabifychallenge.core.data.database.mapper.toDomain
import com.mp98.cabifychallenge.core.data.database.mapper.toEntity
import com.mp98.cabifychallenge.core.domain.model.ProductCart
import com.mp98.cabifychallenge.core.domain.repositories.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val appDao: AppDao
): CartRepository {
    override fun getAllProducts(): Flow<List<ProductCart>> {
        return appDao.getAllProducts().map { databaseProducts ->
            databaseProducts.map { it.toDomain() }
        }
    }

    override suspend fun insertProduct(product: ProductCart) {
        appDao.insertProduct(product.toEntity())
    }

    override suspend fun removeProduct(code: String) {
        appDao.removeProduct(code)
    }
}