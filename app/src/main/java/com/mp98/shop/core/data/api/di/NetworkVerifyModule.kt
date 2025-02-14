package com.mp98.shop.core.data.api.di

import com.mp98.shop.core.data.api.service.VerifyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkVerifyModule {

    @Provides
    @Singleton
    @Named("verifyOkHttp")
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    @Named("verifyRetrofit")
    fun provideRetrofit(@Named("verifyOkHttp") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.247:7003/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideVerifyService(@Named("verifyRetrofit") retrofit: Retrofit): VerifyService {
        return retrofit.create(VerifyService::class.java)
    }
}