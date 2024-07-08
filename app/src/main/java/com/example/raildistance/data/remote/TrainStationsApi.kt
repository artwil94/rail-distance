package com.example.raildistance.data.remote

import retrofit2.http.GET
import retrofit2.http.Headers

interface TrainStationsApi {
    @GET("api/v2/main/stations")
    @Headers("X-KOLEO-Version: 1")
    suspend fun getStations(): List<StationDto>
}