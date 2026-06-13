package com.thechance.jokes.domain.validator

import com.thechance.jokes.domain.exception.InvalidWordException
import org.springframework.stereotype.Component

@Component
class WordValidator {
    private val validWordPattern = Regex("^[a-zA-Z\\u0600-\\u06FF]+$")

    fun validateWord(word: String): String {
        val trimmed = word.trim()

        if (trimmed.isBlank()) {
            throw InvalidWordException(
                "Word cannot be empty!"
            )
        }

        if (trimmed.length < 2) {
            throw InvalidWordException(
                "Word is too short!"
            )
        }

        if (trimmed.length > 50) {
            throw InvalidWordException(
                "Word is too long!, max 50 characters!"
            )
        }

        if (trimmed.all { it.isDigit() }) {
            throw InvalidWordException(
                "Numbers are not allowed!"
            )
        }

        if (!trimmed.matches(validWordPattern)) {
            throw InvalidWordException(
                "Only Arabic or English letters are allowed!"
            )
        }

        return trimmed
    }
}