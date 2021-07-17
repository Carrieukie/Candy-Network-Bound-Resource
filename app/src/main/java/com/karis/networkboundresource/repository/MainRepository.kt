package com.karis.networkboundresource.repository

import androidx.room.withTransaction
import com.karis.networkboundresource.data.network.ApiService
import com.karis.networkboundresource.data.room.Appdatabase
import com.karis.networkboundresource.utils.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject


class MainRepository @Inject constructor(
    private val database: Appdatabase,
    private val apiService : ApiService,
) {

    private val weatherDao = database.charactersDao()

    fun getCharacters() = networkBoundResource(

        query = {
            weatherDao.getWeather()
        },
        fetch = {
            delay(2000)
            apiService.getCharacters()
        },
        saveFetchResult = { characters ->
            database.withTransaction {
                weatherDao.deleteAllWeather()
                weatherDao.insert(characters)
            }
        }
    )


}