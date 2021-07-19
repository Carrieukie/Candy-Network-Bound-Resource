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
        // pass in the logic to query data from the database
        query = {
            weatherDao.getCandy()
        },
        // pass in the logic to fetch data from the api
        fetch = {
            delay(2000)
            apiService.getCandy()
        },

        //pass in the logic to save the result to the local cache
        saveFetchResult = { candys ->
            database.withTransaction {
                weatherDao.deleteAllCandy()
                weatherDao.insertCandy(candys)
            }
        },

        //pass in the logic to determine if the networking call should be made
        shouldFetch = {candys ->
            candys.isEmpty()
        }
    )


}