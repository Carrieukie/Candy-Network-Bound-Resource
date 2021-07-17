package com.example.mytv.data.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.karis.networkboundresource.models.Location

class LocationConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromClouds(clouds: Location?): String? {
        val type = object : TypeToken<Location?>() {}.type
        return gson.toJson(clouds, type)
    }

    @TypeConverter
    fun toClouds(clouds: String?) : Location {

        val type = object : TypeToken<Location>() {}.type
        return gson.fromJson(clouds, type)

    }
}