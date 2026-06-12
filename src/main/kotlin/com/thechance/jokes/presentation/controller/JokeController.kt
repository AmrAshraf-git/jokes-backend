package com.thechance.jokes.presentation.controller

import com.thechance.jokes.domain.usecase.GenerateJokeUseCase
import com.thechance.jokes.presentation.dto.ErrorResponse
import com.thechance.jokes.presentation.dto.toResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/AmrAshraf")
class JokeController(private val generateJokeUseCase: GenerateJokeUseCase) {

    @GetMapping("/joke")
    fun getJoke(
        @RequestParam(value = "word", required = true) word: String
    ): ResponseEntity<Any> {
        return try {
            val joke = generateJokeUseCase(word)
            ResponseEntity.ok(joke.toResponse())
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(
                ErrorResponse(
                    error = "BAD_REQUEST",
                    message = e.message ?: "Invalid input",
                    word = word
                )
            )
        } catch (e: Exception) {
            ResponseEntity.internalServerError().body(
                ErrorResponse(
                    error = "INTERNAL_ERROR",
                    message = "Something went wrong, try again!",
                    word = word
                )
            )
        }
    }

}