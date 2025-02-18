package com.safwa.application.di

import com.safwa.application.data.datasource.api.ApiServices
import com.safwa.application.data.datasource.api.RetrofitHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideApiService(): ApiServices {
        return RetrofitHelper.retrofitInstance.create(ApiServices::class.java)
    }

}