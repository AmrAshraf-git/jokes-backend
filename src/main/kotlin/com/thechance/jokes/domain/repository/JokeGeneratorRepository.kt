package com.thechance.jokes.domain.repository

import com.thechance.jokes.domain.model.Joke
import com.thechance.jokes.domain.model.JokeCommand

interface JokeGeneratorRepository {
    fun generateJoke(jokeCommand: JokeCommand): Joke
}