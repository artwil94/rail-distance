package com.example.raildistance.domain.model

import com.example.raildistance.data.remote.StationKeywordDto

data class StationKeyword(
    val id: Int,
    val keyword: String,
    val stationId: Int
)

fun StationKeywordDto.toStationKeyword(): StationKeyword? {
    return if (id != null && keyword != null && stationId != null) {
        StationKeyword(
            id = id,
            keyword = keyword,
            stationId = stationId
        )
    } else {
        null
    }
}