package com.thechance.jokes.presentation.exception
import com.thechance.jokes.domain.exception.EmptyResponseException
import com.thechance.jokes.domain.exception.InvalidWordException
import com.thechance.jokes.domain.exception.JokeGenerationException
import com.thechance.jokes.presentation.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.resource.NoResourceFoundException

@RestControllerAdvice
class GlobalExceptionHandler {

    // ── Domain Exceptions ──────────────────────────────────────

    @ExceptionHandler(InvalidWordException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleInvalidWord(ex: InvalidWordException): ErrorResponse {
        return ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = "INVALID_WORD",
            message = ex.message ?: "Invalid word provided"
        )
    }

    @ExceptionHandler(EmptyResponseException::class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    fun handleEmptyResponse(ex: EmptyResponseException): ErrorResponse {
        return ErrorResponse(
            status = HttpStatus.BAD_GATEWAY.value(),
            error = "EMPTY_RESPONSE",
            message = ex.message ?: "Received empty response from AI"
        )
    }

    @ExceptionHandler(JokeGenerationException::class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    fun handleJokeGeneration(ex: JokeGenerationException): ErrorResponse {
        return ErrorResponse(
            status = HttpStatus.SERVICE_UNAVAILABLE.value(),
            error = "GENERATION_FAILED",
            message = ex.message ?: "Failed to generate joke"
        )
    }

    // ── Spring Exceptions ──────────────────────────────────────

    @ExceptionHandler(MissingServletRequestParameterException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMissingParam(ex: MissingServletRequestParameterException): ErrorResponse {
        return ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = "MISSING_PARAMETER",
            message = "Parameter '${ex.parameterName}' is required!"
        )
    }

    @ExceptionHandler(NoResourceFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(ex: NoResourceFoundException): ErrorResponse {
        return ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = "NOT_FOUND",
            message = "Endpoint not found! Use GET /AmrAshraf/joke?word=YOUR_WORD"
        )
    }

    // ── Fallback ───────────────────────────────────────────────

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleGeneral(ex: Exception): ErrorResponse {
        return ErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = "INTERNAL_ERROR",
            message = "Something went wrong, try again!"
        )
    }
}