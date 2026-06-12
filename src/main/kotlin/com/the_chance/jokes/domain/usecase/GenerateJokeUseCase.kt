package com.the_chance.jokes.domain.usecase

import com.the_chance.jokes.domain.model.Joke
import com.the_chance.jokes.domain.model.JokeCommand
import com.the_chance.jokes.domain.repository.JokeGeneratorRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class GenerateJokeUseCase(
    private val jokeRepository: JokeGeneratorRepository
) {
    private val dialect = "Egyptian Arabic"

    private val rules = listOf(
        "تكون خفيفة الدم ومضحكة فعلاً",
        "تكون نكتة كاملة لها setup و punchline",
        "تجنب النكت الضعيفة أو التلاعب المباشر بالكلمة",
        "تكون قصيرة (1-5 سطور)",
        "مختلفة كل مرة",
        "بدون عنصرية أو إساءة",
        )

    private val personas = listOf(
        "أنت كوميديان مصري مشهور",
        "أنت مقدم برنامج كوميدي.",
        "أنت كاتب نكت مصري."
    )

    operator fun invoke(word: String): Joke {
        val validWord = validateWord(word)
        val jokeCommand = JokeCommand(
            word = validWord,
            dialect = dialect,
            rules = rules,
            persona = personas.random(),
            seed = UUID.randomUUID().toString().take(8)
        )

        return jokeRepository.generateJoke(jokeCommand)
    }

    private fun validateWord(word: String): String {
        return when {
            word.isBlank() -> throw IllegalArgumentException(
                "Word cannot be empty"
            )

            word.length > 50 -> throw IllegalArgumentException(
                "Word is too long, max 50 characters"
            )

            else -> word.trim()
        }
    }
}