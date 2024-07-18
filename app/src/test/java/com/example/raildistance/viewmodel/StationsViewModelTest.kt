package com.example.raildistance.viewmodel

import com.example.raildistance.domain.model.StationKeyword
import com.example.raildistance.domain.model.TrainStation
import com.example.raildistance.domain.repository.TrainStationsRepository
import com.example.raildistance.stations.StationsViewModel
import com.example.raildistance.util.Response
import com.example.raildistance.utils.calculateDistanceInKilometers
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class StationsViewModelTest {

    private lateinit var repository: TrainStationsRepository
    private lateinit var viewModel: StationsViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        viewModel = StationsViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getTrainStations success updates uiState correctly`() = runTest {
        val stations = listOf(
            TrainStation(
                id = 263452,
                name = "Warszawa Targówek",
                latitude = 52.26378,
                longitude = 21.05374,
            )
        )
        coEvery { repository.getTrainStations() } returns Response.Success(stations)

        viewModel.getTrainStations()

        advanceUntilIdle()

        assertEquals(viewModel.uiState.trainStations, stations)
        assertFalse(viewModel.uiState.isLoading)
        assertNull(viewModel.uiState.error)
    }

    @Test
    fun `getTrainStations error updates uiState correctly`() = runTest {
        val stations = listOf(
            TrainStation(
                id = 263452,
                name = "Warszawa Targówek",
                latitude = 52.26378,
                longitude = 21.05374,
            )
        )
        coEvery { repository.getTrainStations() } returns Response.Error(
            data = stations,
            message = "Error"
        )

        viewModel.getTrainStations()

        advanceUntilIdle()

        assertNull(viewModel.uiState.trainStations)
        assertFalse(viewModel.uiState.isLoading)
        assertEquals(viewModel.uiState.error, "API response error")
    }

    @Test
    fun `filterStations filters correctly`() = runTest {
        val station1 =
            TrainStation(id = 1, name = "Station 1", latitude = 52.26378, longitude = 21.05374)
        val station2 = TrainStation(
            id = 2,
            name = "Station 2",
            latitude = 53.5481705080276,
            longitude = 18.1123450777675
        )
        val stations = listOf(station1, station2)
        val keywords = listOf(
            StationKeyword(id = 1, stationId = 1, keyword = "station"),
            StationKeyword(id = 2, stationId = 2, keyword = "sta")
        )

        coEvery { repository.getTrainStations() } returns Response.Success(stations)
        coEvery { repository.getStationKeywords() } returns Response.Success(keywords)

        viewModel.getTrainStations()
        viewModel.getStationKeywords()

        advanceUntilIdle()

        viewModel.filterStations("station")

        advanceUntilIdle()

        assertEquals(viewModel.uiState.filteredStations, listOf(station1))
    }

    @Test
    fun `onFirstStationSelect updates firstStation correctly`() = runTest {
        val station =
            TrainStation(id = 1, name = "Station 1", latitude = 52.26378, longitude = 21.05374)

        viewModel.onFirstStationSelect(station)

        advanceUntilIdle()

        assertEquals(viewModel.uiState.firstStation, station)
    }

    @Test
    fun `onSecondStationSelect updates secondStation correctly`() = runTest {
        val station = TrainStation(
            id = 2,
            name = "Station 2",
            latitude = 53.5481705080276,
            longitude = 18.1123450777675
        )

        viewModel.onSecondStationSelect(station)

        advanceUntilIdle()

        assertEquals(viewModel.uiState.secondStation, station)
    }

    @Test
    fun `resetDistanceCalculator resets distance`() = runTest {
        viewModel.resetDistanceCalculator()

        advanceUntilIdle()

        assertNull(viewModel.uiState.distance)
    }

    @Test
    fun `calculateDistanceInKilometers returns correct distance`() {
        val firstStation = TrainStation(
            id = 1,
            name = "Warszawa Główna",
            latitude = 52.22527777,
            longitude = 20.98361111
        )
        val secondStation = TrainStation(
            id = 2,
            name = "Kraków Główny",
            latitude = 50.068509,
            longitude = 19.947983
        )

        val result = calculateDistanceInKilometers(firstStation, secondStation)
        val resultInKilometers = result.toDouble()

        val expectedDistance = 250.00
        val delta = 2.00
        assertEquals(expectedDistance, resultInKilometers,delta)
    }
}
