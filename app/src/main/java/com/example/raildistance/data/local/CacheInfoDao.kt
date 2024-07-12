package com.example.raildistance.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CacheInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cacheInfo: CacheInfo)

    @Query("SELECT * FROM cache_info WHERE id = 0 LIMIT 1")
    suspend fun getCacheInfo(): CacheInfo?
}