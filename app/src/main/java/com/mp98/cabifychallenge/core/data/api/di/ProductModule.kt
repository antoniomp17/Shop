package com.mp98.cabifychallenge.core.data.api.di

import com.mp98.cabifychallenge.core.data.api.repositories.ProductRepositoryImpl
import com.mp98.cabifychallenge.core.domain.repositories.ProductRepository
import com.mp98.cabifychallenge.core.domain.usecases.GetProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductModule {

    @Provides
    @Singleton
    fun provideProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository = productRepositoryImpl

    @Provides
    @Singleton
    fun provideGetProductsUseCase(
        productRepository: ProductRepository
    ): GetProductsUseCase = GetProductsUseCase(productRepository)
}