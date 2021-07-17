package com.karis.networkboundresource.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mytv.data.room.converters.ListItemConverter
import com.example.mytv.data.room.converters.LocationConverter
import com.karis.networkboundresource.data.room.converters.OriginConverter
import com.karis.networkboundresource.models.Response


@Database(entities = [Response::class], version = 1, exportSchema = false)
@TypeConverters(
    ListItemConverter::class,
    LocationConverter::class,
    OriginConverter::class
)
abstract class Appdatabase : RoomDatabase() {

    abstract fun charactersDao(): CharactersDao

}