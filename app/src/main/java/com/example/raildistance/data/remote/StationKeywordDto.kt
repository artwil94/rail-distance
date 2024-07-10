package com.example.raildistance.data.remote

import com.google.gson.annotations.SerializedName

data class StationKeywordDto(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("keyword") val keyword: String? = null,
    @SerializedName("station_id") val stationId: Int? = null
)