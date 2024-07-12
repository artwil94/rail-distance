package com.example.raildistance.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.raildistance.domain.model.StationKeyword

@Dao
interface StationKeywordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(keywords: List<StationKeyword>)

    @Query("SELECT * FROM station_keyword")
    suspend fun getAllKeywords(): List<StationKeyword>

    @Query("DELETE FROM station_keyword")
    suspend fun deleteAll()

}