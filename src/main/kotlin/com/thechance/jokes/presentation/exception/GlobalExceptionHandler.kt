package com.thechance.jokes.presentation.exception
import com.thechance.jokes.domain.exception.EmptyResponseException
import com.thechance.jokes.domain.exception.InvalidWordException
import com.thechance.jokes.domain.exception.JokeGenerationException
import com.thechance.jokes.presentation.dto.ApiResponse
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
    fun handleInvalidWord(ex: InvalidWordException): ApiResponse<ErrorResponse> {
        return ApiResponse(
            status = "error",
            code = HttpStatus.BAD_REQUEST.value(),
            data = ErrorResponse(
                error = "INVALID_WORD",
                message = ex.message ?: "Invalid word provided",
            )
        )
    }

    @ExceptionHandler(EmptyResponseException::class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    fun handleEmptyResponse(ex: EmptyResponseException): ApiResponse<ErrorResponse> {
        return ApiResponse(
            status = "error",
            code = HttpStatus.BAD_GATEWAY.value(),
            data = ErrorResponse(
                error = "EMPTY_RESPONSE",
                message = ex.message ?: "Received empty response from AI",
            )
        )
    }

    @ExceptionHandler(JokeGenerationException::class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    fun handleJokeGeneration(ex: JokeGenerationException): ApiResponse<ErrorResponse> {
        return ApiResponse(
            status = "error",
            code = HttpStatus.SERVICE_UNAVAILABLE.value(),
            data = ErrorResponse(
                error = "GENERATION_FAILED",
                message = ex.message ?: "Failed to generate joke",
            )
        )
    }

    // ── Spring Exceptions ──────────────────────────────────────

    @ExceptionHandler(MissingServletRequestParameterException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMissingParam(ex: MissingServletRequestParameterException): ApiResponse<ErrorResponse> {
        return ApiResponse(
            status = "error",
            code = HttpStatus.BAD_REQUEST.value(),
            data = ErrorResponse(
                error = "MISSING_PARAMETER",
                message = "Parameter '${ex.parameterName}' is required!",
            )
        )
    }

    @ExceptionHandler(NoResourceFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(ex: NoResourceFoundException): ApiResponse<ErrorResponse> {
        return ApiResponse(
            status = "error",
            code = HttpStatus.NOT_FOUND.value(),
            data = ErrorResponse(
                error = "NOT_FOUND",
                message = "Endpoint not found! ",
            )
        )
    }

    // ── Fallback ───────────────────────────────────────────────

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleGeneral(ex: Exception): ApiResponse<ErrorResponse> {
        return ApiResponse(
            status = "error",
            code = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            data = ErrorResponse(
                error = "INTERNAL_ERROR",
                message = "Something went wrong, try again!",
            )
        )
    }
}