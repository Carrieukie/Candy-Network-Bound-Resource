package com.karis.networkboundresource.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.karis.networkboundresource.models.CandyItem


@Database(entities = [CandyItem::class], version = 1, exportSchema = false)
abstract class Appdatabase : RoomDatabase() {

    abstract fun charactersDao(): CandyDao

}