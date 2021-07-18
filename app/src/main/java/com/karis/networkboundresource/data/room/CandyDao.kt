package com.karis.networkboundresource.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karis.networkboundresource.models.CandyItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CandyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCandy(candyItem: List<CandyItem>)

    @Query("SELECT * FROM candy")
    fun getCandy() : Flow<List<CandyItem>>

    @Query("DELETE FROM candy")
    fun deleteAllCandy()


}