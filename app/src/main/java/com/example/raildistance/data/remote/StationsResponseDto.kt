package com.example.raildistance.data.remote

import com.google.gson.annotations.SerializedName

data class StationDto(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("name_slug") val nameSlug: String? = null,
    @SerializedName("latitude") val latitude: Double? = null,
    @SerializedName("longitude") val longitude: Double? = null,
    @SerializedName("hits") val hits: Int? = null,
    @SerializedName("ibnr") val ibnr: Int? = null,
    @SerializedName("city") val city: String? = null,
    @SerializedName("region") val region: String? = null,
    @SerializedName("country") val country: String? = null,
    @SerializedName("localised_name") val localisedName: String? = null,
    @SerializedName("is_group") val isGroup: Boolean? = null,
    @SerializedName("has_announcements") val hasAnnouncements: Boolean? = null,
    @SerializedName("is_nearby_station_enabled") val isNearbyStationEnabled: Boolean? = null
)