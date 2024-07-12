package com.example.raildistance.utils

import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CharacterConversionTest {
    @Test
    fun testConvertPolishCharacters() {
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
}