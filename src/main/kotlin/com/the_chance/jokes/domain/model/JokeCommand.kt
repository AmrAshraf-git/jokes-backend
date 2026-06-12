package com.the_chance.jokes.domain.model

data class JokeCommand(
    val word: String,
    val dialect: String,
    val rules: List<String>,
    val persona: String,
    val seed: String
)