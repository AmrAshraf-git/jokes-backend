package com.thechance.jokes.domain.exception

sealed class JokeException(message: String) : RuntimeException(message)

class InvalidWordException(message: String) : JokeException(message)

class JokeGenerationException(message: String) : JokeException(message)

class EmptyResponseException(message: String) : JokeException(message)