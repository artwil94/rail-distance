package com.example.raildistance.domain.model

import com.example.raildistance.data.remote.StationDto

data class TrainStations(
    val stations: List<StationDto>? = null
)
