package com.example.raildistance.utils

fun normalizePolishCharacters(input: String): String {
    val original = arrayOf("ą", "ć", "ę", "ł", "ń", "ó", "ś", "ż", "ź")
    val normalized = arrayOf("a", "c", "e", "l", "n", "o", "s", "z", "z")

    var result = input.lowercase()
    original.indices.forEach { index ->
        result = result.replace(original[index], normalized[index], ignoreCase = true)
    }
    return result
}