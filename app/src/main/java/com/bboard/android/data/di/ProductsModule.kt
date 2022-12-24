package com.bboard.android.data.di

import com.bboard.android.data.remote.repository.ProductsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ProductsModule {
    @Provides
    fun provideService() = ApiModule.provideService(ProductsApi::class.java)
}