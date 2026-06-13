package com.thechance.jokes.presentation.controller

import com.thechance.jokes.domain.usecase.GenerateJokeUseCase
import com.thechance.jokes.presentation.dto.JokeResponse
import com.thechance.jokes.presentation.dto.toResponse
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
    ): JokeResponse {
        val joke = generateJokeUseCase(word)
        return joke.toResponse()
    }
}