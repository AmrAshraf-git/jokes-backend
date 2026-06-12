package com.thechance.jokes.data.remote.gemini

import com.thechance.jokes.config.GeminiProperties
import com.thechance.jokes.data.remote.gemini.dto.ContentDto
import com.thechance.jokes.data.remote.gemini.dto.GeminiRequest
import com.thechance.jokes.data.remote.gemini.dto.GeminiResponse
import com.thechance.jokes.data.remote.gemini.dto.GenerationConfigDto
import com.thechance.jokes.data.remote.gemini.dto.PartDto
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient


@Component
class GeminiClient(
    private val properties: GeminiProperties,
    private val restClient: RestClient)
    {
    private val logger = LoggerFactory.getLogger(GeminiClient::class.java)

    fun generate(prompt: String): String {
        val requestBody = buildRequest(prompt)
        val url = "${properties.apiUrl}?key=${properties.apiKey}"

        logger.debug("Calling Gemini API")

        val response = restClient.post()
            .uri(url)
            .body(requestBody)
            .retrieve()
            .body(GeminiResponse::class.java)

        return extractText(response)
    }

    private fun buildRequest(prompt: String) = GeminiRequest(
        contents = listOf(
            ContentDto(parts = listOf(PartDto(text = prompt)))
        ),
        generationConfig = GenerationConfigDto()
    )

    private fun extractText(response: GeminiResponse?): String {
        return response?.candidates
            ?.firstOrNull()
            ?.content
            ?.parts
            ?.firstOrNull()
            ?.text
            ?.trim()
            ?: throw RuntimeException("Empty response from Gemini API")
    }
}