package com.thechance.jokes.data.remote.gemini.repository

import com.thechance.jokes.data.remote.gemini.GeminiClient
import com.thechance.jokes.data.remote.gemini.helper.PromptBuilder
import com.thechance.jokes.domain.model.Joke
import com.thechance.jokes.domain.model.JokeCommand
import com.thechance.jokes.domain.repository.JokeGeneratorRepository
import org.springframework.stereotype.Repository

@Repository
class JokeGeneratorRepositoryImpl(
    private val geminiApiClient: GeminiClient,
    private val promptBuilder: PromptBuilder
) : JokeGeneratorRepository {

    override fun generateJoke(jokeCommand: JokeCommand): Joke {
        val prompt = promptBuilder.build(jokeCommand)
        val content = geminiApiClient.generate(prompt)
        return Joke(
            word = jokeCommand.word,
            content = content,
            requestId = jokeCommand.seed,
            dialect = jokeCommand.dialect
        )
    }
}