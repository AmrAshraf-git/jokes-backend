package com.the_chance.jokes.data.remote.gemini.dto

data class ContentDto(
    val parts: List<PartDto>,
    val role: String = "user"
)
