package com.example.raildistance.domain.repository

import com.example.raildistance.data.remote.TrainStationsApi
import com.example.raildistance.domain.model.StationKeyword
import com.example.raildistance.domain.model.TrainStation
import com.example.raildistance.domain.model.toStationKeyword
import com.example.raildistance.domain.model.toTrainStation
import com.example.raildistance.util.Response
import javax.inject.Inject

class TrainStationsRepositoryImpl @Inject constructor(private val stationsApi: TrainStationsApi) :
    TrainStationsRepository {

    override suspend fun getTrainStations(): Response<List<TrainStation>> {
        return try {
            val result = stationsApi.getStations()
            val stations = result.map { it.toTrainStation() }.requireNoNulls()
            Response.Success(
                data = stations
            )
        } catch (e: Exception) {
            Response.Error(message = e.message)
        }
    }

    override suspend fun getStationKeywords(): Response<List<StationKeyword>> {
        return try {
            val result = stationsApi.getStationKeyWords()
            val keywords = result.map { it.toStationKeyword() }.requireNoNulls()
            Response.Success(
                data = keywords
            )
        } catch (e: Exception) {
            Response.Error(message = e.message)
        }
    }
}