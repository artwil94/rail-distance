package com.example.raildistance.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.raildistance.domain.model.TrainStation

@Dao
interface TrainStationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(trainStations: List<TrainStation>)

    @Query("SELECT * FROM train_station WHERE id = :id")
    suspend fun getTrainStationById(id: Int): TrainStation?

    @Query("SELECT * FROM train_station")
    suspend fun getAllTrainStations(): List<TrainStation>

    @Query("DELETE FROM train_station")
    suspend fun deleteAll()
}