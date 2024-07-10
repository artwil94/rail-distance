package com.example.raildistance.domain.model

import com.example.raildistance.data.remote.StationDto

data class TrainStation(
    val id: Int,
    val name: String,
    val nameSlug: String? = null,
    val latitude: Double,
    val longitude: Double,
    val hits: Int? = null,
    val ibnr: Int? = null,
    val city: String? = null,
    val region: String? = null,
    val country: String? = null,
    val localisedName: String? = null,
    val isGroup: Boolean? = null,
    val hasAnnouncements: Boolean? = null,
    val isNearbyStationEnabled: Boolean? = null
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