package com.example.raildistance.utils

import android.location.Location
import com.example.raildistance.domain.model.TrainStation

fun calculateDistanceInKilometers(firstStation: TrainStation, secondStation: TrainStation): String {
    val locationA = Location("point A").apply {
        latitude = firstStation.latitude
        longitude = firstStation.longitude
    }
    val locationB = Location("point B").apply {
        latitude = secondStation.latitude
        longitude = secondStation.longitude
    }
    val distance = locationA.distanceTo(locationB) / 1000
    val formattedDistance = String.format("%.2f", distance)

    return formattedDistance
}