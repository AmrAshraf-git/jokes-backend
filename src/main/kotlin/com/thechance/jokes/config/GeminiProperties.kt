package com.thechance.jokes.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "gemini.api")
data class GeminiProperties(
    val apiKey: String,
    val apiUrl: String,
)
