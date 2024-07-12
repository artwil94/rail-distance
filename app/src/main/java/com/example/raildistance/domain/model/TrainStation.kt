package com.example.raildistance.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.raildistance.data.remote.StationDto

@Entity(tableName = "train_station")
data class TrainStation(
    @PrimaryKey(autoGenerate = true) val itemId: Int? = null,
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "name_slug") val nameSlug: String? = null,
    @ColumnInfo(name = "latitude") val latitude: Double,
    @ColumnInfo(name = "longitude") val longitude: Double,
    @ColumnInfo(name = "hits") val hits: Int? = null,
    @ColumnInfo(name = "ibnr") val ibnr: Int? = null,
    @ColumnInfo(name = "city") val city: String? = null,
    @ColumnInfo(name = "region") val region: String? = null,
    @ColumnInfo(name = "country") val country: String? = null,
    @ColumnInfo(name = "localised_name") val localisedName: String? = null,
    @ColumnInfo(name = "is_group") val isGroup: Boolean? = null,
    @ColumnInfo(name = "has_announcements") val hasAnnouncements: Boolean? = null,
    @ColumnInfo(name = "is_nearby_station_enabled") val isNearbyStationEnabled: Boolean? = null
)

fun StationDto.toTrainStation(): TrainStation? {
    return if (id != null && name != null && latitude != null && longitude != null) {
        TrainStation(
            id = id,
            name = name,
            nameSlug = nameSlug,
            latitude = latitude,
            longitude = longitude,
            hits = hits,
            ibnr = ibnr,
            city = city,
            region = region,
            country = country,
            localisedName = localisedName,
            isGroup = isGroup,
            hasAnnouncements = hasAnnouncements,
            isNearbyStationEnabled = isNearbyStationEnabled
        )
    } else {
        null
    }
}