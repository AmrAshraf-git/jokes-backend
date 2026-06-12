package com.the_chance.jokes.domain.repository

import com.the_chance.jokes.domain.model.Joke
import com.the_chance.jokes.domain.model.JokeCommand

interface JokeGeneratorRepository {
    fun generateJoke(jokeCommand: JokeCommand): Joke
}