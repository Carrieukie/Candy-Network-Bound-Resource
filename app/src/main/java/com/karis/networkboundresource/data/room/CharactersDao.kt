package com.karis.networkboundresource.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karis.networkboundresource.models.Response
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(response: Response)

    @Query("SELECT * FROM response")
    fun getWeather() : Flow<Response>

    @Query("DELETE FROM response")
    fun deleteAllWeather()


}