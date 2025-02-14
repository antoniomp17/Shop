package com.mp98.shop.core.data.api.di

import com.mp98.shop.core.data.api.service.ProductService
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
object NetworkShopModule {

    @Provides
    @Singleton
    @Named("shopOkHttp")
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    @Named("shopRetrofit")
    fun provideRetrofit(@Named("shopOkHttp") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.jsonkeeper.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideProductService(@Named("shopRetrofit") retrofit: Retrofit): ProductService {
        return retrofit.create(ProductService::class.java)
    }
}