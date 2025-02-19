package com.mp98.shop.core.data.api.di

import com.mp98.shop.core.data.api.repositories.VerifyRepositoryImpl
import com.mp98.shop.core.domain.repositories.VerifyRepository
import com.mp98.shop.core.domain.usecases.InitVerifierSessionUseCase
import com.mp98.shop.core.domain.usecases.StartListeningSessionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VerifierModule {

    @Provides
    @Singleton
    fun provideVerifierRepository(
        verifyRepositoryImpl: VerifyRepositoryImpl
    ): VerifyRepository = verifyRepositoryImpl

    @Provides
    @Singleton
    fun provideInitVerifierSessionUseCase(
        verifyRepository: VerifyRepository
    ): InitVerifierSessionUseCase = InitVerifierSessionUseCase(verifyRepository)

    @Provides
    @Singleton
    fun provideStartListeningSessionUseCase(
        verifyRepository: VerifyRepository
    ): StartListeningSessionUseCase = StartListeningSessionUseCase(verifyRepository)

}