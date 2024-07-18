package com.example.raildistance.utils

import com.example.raildistance.domain.model.TrainStation
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

fun calculateDistanceInKilometers(
    firstStation: TrainStation,
    secondStation: TrainStation,
): Double {
    val degToRad = 0.017453292519943295 // PI / 180
    val earthRadiusKilometers = 6371.0

    val lat1 = firstStation.latitude
    val lon1 = firstStation.longitude
    val lat2 = secondStation.latitude
    val lon2 = secondStation.longitude
    val lat1Rad = lat1 * degToRad
    val lon1Rad = lon1 * degToRad
    val lat2Rad = lat2 * degToRad
    val lon2Rad = lon2 * degToRad

    val deltaLat = lat2Rad - lat1Rad
    val deltaLon = lon2Rad - lon1Rad

    val a = sin(deltaLat / 2) * sin(deltaLat / 2) +
            cos(lat1Rad) * cos(lat2Rad) *
            sin(deltaLon / 2) * sin(deltaLon / 2)
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))

    return earthRadiusKilometers * c
}

fun distanceConverter(distance: Double): String {
    return String.format("%.2f", distance)
}