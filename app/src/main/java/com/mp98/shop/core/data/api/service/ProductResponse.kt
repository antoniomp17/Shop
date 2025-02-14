package com.mp98.shop.core.data.api.service

import com.mp98.shop.core.data.api.model.ProductEntity
import retrofit2.http.GET

data class ProductResponse(
    val products: List<ProductEntity>
)

interface ProductService {
    @GET("b/22I3")
    suspend fun getProducts(): ProductResponse
}