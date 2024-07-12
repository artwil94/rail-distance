package com.example.raildistance.stations

import android.location.Location
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.raildistance.domain.model.StationKeyword
import com.example.raildistance.domain.model.TrainStation
import com.example.raildistance.domain.repository.TrainStationsRepository
import com.example.raildistance.util.Response
import com.example.raildistance.utils.Constants
import com.example.raildistance.utils.normalizePolishCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class /**/ StationsViewModel @Inject constructor(
    private val repository: TrainStationsRepository,
) : ViewModel() {

    var uiState by mutableStateOf(StationsUIState())
        private set

    fun getTrainStations() {
        viewModelScope.launch {
            uiState = uiState.copy(
                isLoading = true
            )
            val result = repository.getTrainStations()
            Timber.d("ARTUR ${result.message}")
            when (result) {
                is Response.Success -> {
                    uiState = uiState.copy(
                        trainStations = result.data,
                        isLoading = false,
                        error = null
                    )
                }

                is Response.Error -> {
                    uiState = uiState.copy(
                        trainStations = null,
                        error = "API response error",
                        isLoading = false
                    )
                }
            }
        }
    }

    fun getStationKeywords() {
        viewModelScope.launch {
            uiState = uiState.copy(
                isLoading = true
            )
            when (val result = repository.getStationKeywords()) {
                is Response.Success -> {
                    uiState = uiState.copy(
                        keywords = result.data,
                        isLoading = false,
                        error = null
                    )
                }

                is Response.Error -> {
                    uiState = uiState.copy(
                        keywords = null,
                        error = "API response error",
                        isLoading = false
                    )
                }
            }
        }
    }

    fun filterStations(input: String) {
        viewModelScope.launch {
            if (uiState.trainStations != null && uiState.keywords != null) {
                val keywords = uiState.keywords!!
                val stations = uiState.trainStations!!
                if (input.length >= Constants.MIN_INPUT_LENGTH_TO_AUTOCOMPLETE) {
                    val filteredKeywords = keywords.filter {
                        normalizePolishCharacters(it.keyword).contains(input.lowercase())
                    }
                    val matchingStationIDs = filteredKeywords.map { it.stationId }.toSet()
                    val result = stations.filter {
                        matchingStationIDs.contains(it.id)
                    }.sortedByDescending { it.hits }
                    uiState = uiState.copy(
                        filteredStations = result,
                        error = null,
                        isLoading = false
                    )
                } else {
                    uiState = uiState.copy(
                        filteredStations = emptyList(),
                        error = null,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun onFirstStationSelect(station: TrainStation) {
        viewModelScope.launch {
            uiState = uiState.copy(
                firstStation = station
            )
        }
    }

    fun onSecondStationSelect(station: TrainStation) {
        viewModelScope.launch {
            uiState = uiState.copy(
                secondStation = station
            )
        }
    }

    fun resetDistanceCalculator() {
        viewModelScope.launch {
            uiState = uiState.copy(
                distance = null
            )
        }
    }

    fun calculateDistance(firstStation: TrainStation, secondStation: TrainStation) {
        viewModelScope.launch {
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
            Timber.d("ARTURW $formattedDistance")
            uiState = uiState.copy(
                distance = formattedDistance
            )
        }
    }
}

data class StationsUIState(
    val isLoading: Boolean = true,
    val error: String? = null,
    var trainStations: List<TrainStation>? = listOf(),
    var filteredStations: List<TrainStation>? = listOf(),
    var keywords: List<StationKeyword>? = listOf(),
    var firstStation: TrainStation? = null,
    var secondStation: TrainStation? = null,
    var distance: String? = null
)