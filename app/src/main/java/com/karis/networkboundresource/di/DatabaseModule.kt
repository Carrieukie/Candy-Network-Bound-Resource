package com.karis.networkboundresource.di

import android.app.Application
import androidx.room.Room
import com.karis.networkboundresource.data.room.Appdatabase
import com.karis.networkboundresource.data.room.CharactersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(context: Application) : Appdatabase {
        return Room.databaseBuilder(context, Appdatabase::class.java,"characters_db").build()
    }

    @Provides
    @Singleton
    fun providesWeatherDao(appdatabase: Appdatabase) : CharactersDao {
        return appdatabase.charactersDao()
    }

}