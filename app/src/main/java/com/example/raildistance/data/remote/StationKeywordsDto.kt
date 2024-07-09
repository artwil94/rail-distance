package com.example.raildistance.data.remote

import com.google.gson.annotations.SerializedName

data class StationKeywordsDto(
    @SerializedName("id") val id: Int,
    @SerializedName("keyword") val keyword: String,
    @SerializedName("station_id") val stationId: Int
)