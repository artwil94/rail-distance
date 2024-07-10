package com.example.raildistance.stations

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.raildistance.domain.model.StationKeyword
import com.example.raildistance.domain.model.TrainStation
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
                    Timber.d("ARTUR $result")
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
}

data class StationsUIState(
    val isLoading: Boolean = true,
    val error: String? = null,
    var trainStations: List<TrainStation>? = listOf(),
    var keywords: List<StationKeyword>? = listOf()
)