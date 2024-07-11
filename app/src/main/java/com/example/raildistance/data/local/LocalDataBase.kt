package com.example.raildistance.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.raildistance.domain.model.StationKeyword
import com.example.raildistance.domain.model.TrainStation

@Database(
    entities = [TrainStation::class, CacheInfo::class, StationKeyword::class], version = 1
)
abstract class LocalDataBase : RoomDatabase() {

    abstract val trainStationDao: TrainStationDao
    abstract val cacheInfoDao: CacheInfoDao
    abstract val stationKeywordDao: StationKeywordDao
}