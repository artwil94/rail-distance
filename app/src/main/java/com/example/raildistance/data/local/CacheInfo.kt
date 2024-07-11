package com.example.raildistance.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cache_info")
data class CacheInfo(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = "lastFetchTime")val lastFetchTime: Long
)