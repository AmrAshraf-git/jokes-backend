package com.thechance.jokes.data.remote.gemini.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class GeminiRequest(
    val contents: List<ContentDto>,
    @JsonProperty("generationConfig")
    val generationConfig: GenerationConfigDto
)

data class PartDto(
    val text: String
)

data class GenerationConfigDto(
    val temperature: Float = 1.2f,
    //val topP: Float = 0.95f,
    //val topK: Int = 64,
    //val maxOutputTokens: Int = 1000
)