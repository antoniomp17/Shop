package com.mp98.cabifychallenge.core.data.api.service

import com.mp98.cabifychallenge.core.data.api.model.ProductEntity
import retrofit2.http.GET

data class ProductResponse(
    val products: List<ProductEntity>
)

interface ProductService {
    @GET("6c19259bd32dd6aafa327fa557859c2f/raw/ba51779474a150ee4367cda4f4ffacdcca479887/Products.json")
    suspend fun getProducts(): ProductResponse
}