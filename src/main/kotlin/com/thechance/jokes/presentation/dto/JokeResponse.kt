package com.thechance.jokes.presentation.dto

import com.thechance.jokes.domain.model.Joke

data class JokeResponse(
    val word: String,
    val joke: String,
    val dialect: String,
    val requestId: String
)

fun Joke.toResponse() = JokeResponse(
    word = this.word,
    joke = this.content,
    dialect = this.dialect,
    requestId = this.requestId
)
