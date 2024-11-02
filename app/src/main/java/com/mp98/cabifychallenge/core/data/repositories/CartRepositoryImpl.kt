package com.mp98.cabifychallenge.core.data.repositories

import com.mp98.cabifychallenge.core.data.database.AppDao
import com.mp98.cabifychallenge.core.data.mapper.toDomain
import com.mp98.cabifychallenge.core.data.mapper.toEntity
import com.mp98.cabifychallenge.core.domain.model.Product
import com.mp98.cabifychallenge.core.domain.repositories.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val appDao: AppDao
): CartRepository {
    override fun getAllProducts(): Flow<List<Product>> {
        return appDao.getAllProducts().map { databaseProducts ->
            databaseProducts.map { it.toDomain() }
        }
    }

    override suspend fun insertProduct(product: Product) {
        appDao.insertProduct(product.toEntity())
    }

    override suspend fun removeProduct(code: String) {
        appDao.removeProduct(code)
    }
}