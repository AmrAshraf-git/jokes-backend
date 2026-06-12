package com.the_chance.jokes.data.remote.gemini.repository

import com.the_chance.jokes.data.remote.gemini.GeminiClient
import com.the_chance.jokes.data.remote.gemini.helper.PromptBuilder
import com.the_chance.jokes.domain.model.Joke
import com.the_chance.jokes.domain.model.JokeCommand
import com.the_chance.jokes.domain.repository.JokeGeneratorRepository
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
            requestId = jokeCommand.seed
        )
    }
}