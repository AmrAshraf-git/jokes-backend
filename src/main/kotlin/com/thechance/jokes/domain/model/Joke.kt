package com.thechance.jokes.domain.model

data class Joke(
    val word: String,
    val content: String,
    val requestId: String,
    val dialect: String
)
