package com.thechance.jokes.presentation.dto

data class ApiResponse<T>(
    val status: String,
    val code: Int,
    val data: T? = null,
)