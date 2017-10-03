package io.kida.ktor.middleware.model

data class MiddlewareResult<out T>(val success: Boolean, val result: T?)