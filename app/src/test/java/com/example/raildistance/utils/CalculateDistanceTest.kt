package com.example.raildistance.utils

import android.location.Location
import com.example.raildistance.domain.model.TrainStation
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TrainStationDistanceTest {

    private lateinit var firstStation: TrainStation
    private lateinit var secondStation: TrainStation

    @Before
    fun setUp() {
        firstStation = TrainStation(
            id = 1,
            name = "Station A",
            nameSlug = "station_a",
            latitude = 52.2296756,
            longitude = 21.0122287,
            hits = null,
            ibnr = null,
            city = "Warsaw",
            region = "Mazovia",
            country = "Poland",
            localisedName = null,
            isGroup = null,
            hasAnnouncements = null,
            isNearbyStationEnabled = null
        )

        secondStation = TrainStation(
            id = 2,
            name = "Station B",
            nameSlug = "station_b",
            latitude = 50.0646501,
            longitude = 19.9449799,
            hits = null,
            ibnr = null,
            city = "Krakow",
            region = "Lesser Poland",
            country = "Poland",
            localisedName = null,
            isGroup = null,
            hasAnnouncements = null,
            isNearbyStationEnabled = null
        )
    }

    @Test
    fun testCalculateDistanceInKilometers() {
        val locationA = Location("point A").apply {
            latitude = firstStation.latitude
            longitude = firstStation.longitude
        }
        val locationB = Location("point B").apply {
            latitude = secondStation.latitude
            longitude = secondStation.longitude
        }
        val expectedDistance = locationA.distanceTo(locationB) / 1000
        val formattedExpectedDistance = String.format("%.2f", expectedDistance)

        val actualDistance = calculateDistanceInKilometers(firstStation, secondStation)

        assertEquals(formattedExpectedDistance, actualDistance)
    }
}
