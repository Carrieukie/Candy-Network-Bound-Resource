package com.karis.networkboundresource.data.network

import com.karis.networkboundresource.models.Response
import retrofit2.http.GET

interface ApiService {

    @GET("character")
    suspend fun getCharacters() : Response

}
