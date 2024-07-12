package com.example.raildistance.data

import com.example.raildistance.data.remote.StationDto
import com.example.raildistance.data.remote.TrainStationsApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@RunWith(JUnit4::class)
class ApiServiceTest {
    private val apiService = mock(TrainStationsApi::class.java)

    @Test
    fun testFetchTrainStationsSuccess() = runBlocking {
        // Given
        val expectedStations = listOf(
            StationDto(
                id = 263452,
                name = "Warszawa Targówek",
                latitude = 52.26378,
                longitude = 21.05374,
            )
        )
        `when`(apiService.getStations()).thenReturn(expectedStations)

        // When
        val actualStations = apiService.getStations()

        // Then
        assertEquals(expectedStations, actualStations)
    }

    @Test
    fun testFetchTrainStationsFailure() = runBlocking {
        // Given
        `when`(apiService.getStations()).thenThrow(RuntimeException("API error"))

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