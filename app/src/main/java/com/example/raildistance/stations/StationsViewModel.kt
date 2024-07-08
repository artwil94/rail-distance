package com.example.raildistance.stations

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.raildistance.data.remote.StationDto
import com.example.raildistance.domain.repository.TrainStationsRepository
import com.example.raildistance.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class StationsViewModel @Inject constructor(
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
            Timber.d("ARTURO ${result.message}")
            when (result) {
                is Response.Success -> {
                    uiState = uiState.copy(
                        trainStationDtos = result.data?.stationDtos,
                        isLoading = false,
                        error = null
                    )
                }

                is Response.Error -> {
                    uiState = uiState.copy(
                        trainStationDtos = null,
                        error = "API response error",
                        isLoading = false
                    )
                }
            }
        }
    }
}

data class StationsUIState(
    val isLoading: Boolean = true,
    val error: String? = null,
    var trainStationDtos: List<StationDto>? = listOf()
)