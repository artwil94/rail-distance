package com.example.raildistance.data

import com.example.raildistance.data.remote.StationDto
import com.example.raildistance.data.remote.TrainStationsApi
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ApiServiceTest {
    private val apiService = mockk<TrainStationsApi>()

    @Test
    fun `fetchTrainStations returns expected stations on success`() = runBlocking {
        // Given
        val expectedStations = listOf(
            StationDto(
                id = 263452,
                name = "Warszawa Targówek",
                latitude = 52.26378,
                longitude = 21.05374,
            )
        )
        coEvery { apiService.getStations() } returns expectedStations

        // When
        val actualStations = apiService.getStations()

        // Then
        assertEquals(expectedStations, actualStations)
    }

    @Test
    fun `fetchTrainStations throws exception on failure`() = runBlocking {
        // Given
        coEvery { apiService.getStations() } throws RuntimeException("API error")

        // When
        try {
            apiService.getStations()
            fail("Expected an exception")
        } catch (e: Exception) {
            // Then
            assertEquals("API error", e.message)
        }
    }
}