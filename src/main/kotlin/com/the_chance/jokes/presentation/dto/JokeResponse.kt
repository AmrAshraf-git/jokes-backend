package com.the_chance.jokes.presentation.dto

import com.the_chance.jokes.domain.model.Joke

data class JokeResponse(
    val word: String,
    val joke: String,
    val requestId: String
)

fun Joke.toResponse() = JokeResponse(
    word = this.word,
    joke = this.content,
    requestId = this.requestId
)
