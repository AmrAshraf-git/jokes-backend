package com.the_chance.jokes.data.remote.gemini.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class GeminiResponse(
    val candidates: List<CandidateDto>?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CandidateDto(
    val content: ContentDto?
)