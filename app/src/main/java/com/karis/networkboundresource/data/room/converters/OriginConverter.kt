package com.karis.networkboundresource.data.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.karis.networkboundresource.models.Origin

class OriginConverter {


    private val gson = Gson()

    @TypeConverter
    fun fromCoord(origin: Origin?): String? {
        val type = object : TypeToken<Origin?>() {}.type
        return gson.toJson(origin, type)
    }

    @TypeConverter
    fun toCoord(origin: String?) : Origin {

        val type = object : TypeToken<Origin>() {}.type
        return gson.fromJson(origin, type)
    }

}