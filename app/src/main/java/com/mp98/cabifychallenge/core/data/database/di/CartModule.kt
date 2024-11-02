package com.mp98.cabifychallenge.core.data.database.di

import com.mp98.cabifychallenge.core.data.database.bbdd.AppDao
import com.mp98.cabifychallenge.core.data.database.repositories.CartRepositoryImpl
import com.mp98.cabifychallenge.core.domain.repositories.CartRepository
import com.mp98.cabifychallenge.core.domain.usecases.GetAllCartProductsUseCase
import com.mp98.cabifychallenge.core.domain.usecases.RemoveCartProductUseCase
import com.mp98.cabifychallenge.core.domain.usecases.SetCartProductUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CartModule {
    @Provides
    @Singleton
    fun provideConsumeRepository(appDao: AppDao): CartRepository {
        return CartRepositoryImpl(appDao)
    }

    @Provides
    @Singleton
    fun provideGetAllCartProductsUseCase(repository: CartRepository): GetAllCartProductsUseCase {
        return GetAllCartProductsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideRemoveCartProductUseCase(repository: CartRepository): RemoveCartProductUseCase {
        return RemoveCartProductUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSetCartProductUseCase(repository: CartRepository): SetCartProductUseCase {
        return SetCartProductUseCase(repository)
    }

}