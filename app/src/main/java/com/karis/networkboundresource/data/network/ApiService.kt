package com.karis.networkboundresource.data.network

import com.karis.networkboundresource.models.CandyItem
import retrofit2.http.GET

interface ApiService {

    @GET("api/")
    suspend fun getCandy() : List<CandyItem>

}
