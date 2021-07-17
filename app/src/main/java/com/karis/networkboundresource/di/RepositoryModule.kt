package com.karis.networkboundresource.di

import com.karis.networkboundresource.data.network.ApiService
import com.karis.networkboundresource.data.room.Appdatabase
import com.karis.networkboundresource.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesRepository(appdatabase: Appdatabase, apiService: ApiService) : MainRepository {
        return MainRepository(appdatabase,apiService)
    }

}