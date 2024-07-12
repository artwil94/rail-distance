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
        val original = "Łódź"
        val expected = "Lodz"

        // When
        val actual = convertPolishCharacters(original)

        // Then
        assertEquals(expected, actual)
    }

    private fun convertPolishCharacters(input: String): String {
        return input.replace("Ł", "L")
            .replace("ł", "l")
            .replace("ó", "o")
            .replace("Ó", "O")
            .replace("ź", "z")
            .replace("Ź", "Z")
            .replace("ż", "z")
            .replace("Ż", "Z")
            .replace("ć", "c")
            .replace("Ć", "C")
            .replace("ń", "n")
            .replace("Ń", "N")
            .replace("ą", "a")
            .replace("Ą", "A")
            .replace("ę", "e")
            .replace("Ę", "E")
            .replace("ś", "s")
            .replace("Ś", "S")
    }
}