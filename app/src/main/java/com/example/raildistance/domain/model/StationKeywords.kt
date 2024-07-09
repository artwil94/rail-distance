package com.example.raildistance.domain.model

import com.example.raildistance.data.remote.StationKeywordsDto

data class StationKeywords(
    val keywords: List<StationKeywordsDto>? = null
)