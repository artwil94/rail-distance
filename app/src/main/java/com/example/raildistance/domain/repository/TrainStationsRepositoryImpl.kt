package com.example.raildistance.domain.repository

import com.example.raildistance.data.remote.TrainStationsApi
import com.example.raildistance.domain.model.StationKeywords
import com.example.raildistance.domain.model.TrainStations
import com.example.raildistance.util.Response
import javax.inject.Inject

class TrainStationsRepositoryImpl @Inject constructor(private val stationsApi: TrainStationsApi) :
    TrainStationsRepository {

    override suspend fun getTrainStations(): Response<TrainStations> {
        return try {
            val result = stationsApi.getStations()
            Response.Success(
                data = TrainStations(stations = result)
            )
        } catch (e: Exception) {
            Response.Error(message = e.message)
        }
    }

    override suspend fun getStationKeywords(): Response<StationKeywords> {
        return try {
            val result = stationsApi.getStationKeyWords()
            Response.Success(
                data = StationKeywords(keywords = result)
            )
        } catch (e: Exception) {
            Response.Error(message = e.message)
        }
    }
}