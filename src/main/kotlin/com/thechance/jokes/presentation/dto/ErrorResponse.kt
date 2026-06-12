package com.thechance.jokes.presentation.dto

data class ErrorResponse(
    val error: String,
    val message: String,
    val word: String? = null
)
