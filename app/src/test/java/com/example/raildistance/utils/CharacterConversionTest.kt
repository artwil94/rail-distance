package com.example.raildistance.utils

import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CharacterConversionTest {
    @Test
    fun `normalizePolishCharacters converts characters correctly`() {
        // Given
        val original = ("lódź")
        val expected = ("lodz")

        val originalCapitalize = ("Gdańsk")
        val expectedCapitalize = ("gdansk")

        // When
        val actual = normalizePolishCharacters(original)
        val actualCapitalize = normalizePolishCharacters(originalCapitalize)

        // Then
        assertEquals(expected, actual)
        assertEquals(expectedCapitalize,actualCapitalize)
    }

    @Test
    fun `distanceConverter formats distance correctly`() {
        val distance1 = 123.456
        val expected1 = "123.46"
        assertEquals(expected1, distanceConverter(distance1))

        val distance2 = 123.4
        val expected2 = "123.40"
        assertEquals(expected2, distanceConverter(distance2))

        val distance3 = 123.0
        val expected3 = "123.00"
        assertEquals(expected3, distanceConverter(distance3))

        val distance4 = 0.0
        val expected4 = "0.00"
        assertEquals(expected4, distanceConverter(distance4))
    }
}