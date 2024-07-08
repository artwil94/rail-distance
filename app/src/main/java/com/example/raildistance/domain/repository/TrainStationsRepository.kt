package com.example.raildistance.domain.repository

import com.example.raildistance.domain.model.TrainStations
import com.example.raildistance.util.Response

interface TrainStationsRepository {
    suspend fun getTrainStations(): Response<TrainStations>
}