package com.example.raildistance.domain.repository

import com.example.raildistance.data.local.CacheInfo
import com.example.raildistance.data.local.LocalDataBase
import com.example.raildistance.data.remote.TrainStationsApi
import com.example.raildistance.domain.model.StationKeyword
import com.example.raildistance.domain.model.TrainStation
import com.example.raildistance.domain.model.toStationKeyword
import com.example.raildistance.domain.model.toTrainStation
import com.example.raildistance.util.Response
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class TrainStationsRepositoryImpl @Inject constructor(
    private val stationsApi: TrainStationsApi,
    private val dataBase: LocalDataBase
) :
    TrainStationsRepository {

    override suspend fun getTrainStations(): Response<List<TrainStation>> {
        return try {
            val cacheInfo = dataBase.cacheInfoDao.getCacheInfo()
            val currentTime = System.currentTimeMillis()
            if (cacheInfo == null || isFetchNeeded(cacheInfo.lastFetchTime, currentTime)) {
                val trainStations =
                    stationsApi.getStations().map { it.toTrainStation() }.requireNoNulls()
                Timber.d("ARTURTW local $trainStations")
                dataBase.trainStationDao.deleteAll()
                dataBase.trainStationDao.insertAll(trainStations = trainStations)
                dataBase.cacheInfoDao.insert(CacheInfo(lastFetchTime = currentTime))
                Response.Success(
                    data = trainStations
                )
            } else {
                Response.Success(
                    dataBase.trainStationDao.getAllTrainStations()
                )
            }
        } catch (e: Exception) {
            Response.Error(message = e.message)
        }
    }

    override suspend fun getStationKeywords(): Response<List<StationKeyword>> {
        return try {
            val cacheInfo = dataBase.cacheInfoDao.getCacheInfo()
            val currentTime = System.currentTimeMillis()
            if (cacheInfo == null || isFetchNeeded(cacheInfo.lastFetchTime, currentTime)) {
                val result = stationsApi.getStationKeyWords()
                val keywords = result.map { it.toStationKeyword() }.requireNoNulls()
                dataBase.stationKeywordDao.deleteAll()
                dataBase.stationKeywordDao.insertAll(keywords = keywords)
                Response.Success(
                    data = keywords
                )
            } else {
                Response.Success(
                    data = dataBase.stationKeywordDao.getAllKeywords()
                )
            }
        } catch (e: Exception) {
            Response.Error(message = e.message)
        }
    }

    private fun isFetchNeeded(lastFetchTime: Long, currentTime: Long): Boolean {
        return currentTime - lastFetchTime > TimeUnit.HOURS.toMillis(24)
    }
}