package com.thechance.jokes.domain.usecase

import com.thechance.jokes.domain.exception.InvalidWordException
import com.thechance.jokes.domain.model.Joke
import com.thechance.jokes.domain.model.JokeCommand
import com.thechance.jokes.domain.repository.JokeGeneratorRepository
import com.thechance.jokes.domain.validator.WordValidator
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class GenerateJokeUseCase(
    private val jokeRepository: JokeGeneratorRepository,
    private val validator: WordValidator
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
        val validWord = validator.validateWord(word)
        val jokeCommand = JokeCommand(
            word = validWord,
            dialect = dialect,
            rules = rules,
            persona = personas.random(),
            seed = UUID.randomUUID().toString().take(8)
        )

        return jokeRepository.generateJoke(jokeCommand)
    }
}