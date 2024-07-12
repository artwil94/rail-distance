package com.example.raildistance.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.raildistance.data.remote.StationKeywordDto

@Entity(tableName = "station_keyword")
data class StationKeyword(
    @PrimaryKey val itemId: Int? = null,
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "keyword") val keyword: String?,
    @ColumnInfo(name = "station_id") val stationId: Int?
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