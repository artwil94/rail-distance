package com.example.raildistance.domain.repository

import com.example.raildistance.domain.model.StationKeyword
import com.example.raildistance.domain.model.TrainStation
import com.example.raildistance.util.Response

interface TrainStationsRepository {
    suspend fun getTrainStations(): Response<List<TrainStation>>

    suspend fun getStationKeywords(): Response<List<StationKeyword>>
}