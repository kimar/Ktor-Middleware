package io.kida.ktor.middleware

import io.kida.ktor.middleware.model.MiddlewareResult
import org.jetbrains.ktor.pipeline.PipelineContext

interface Middleware<T: Any, out U: Any> {
    suspend fun pass(ctx: PipelineContext<T>): U?
}

suspend fun <T: Any, U: Any>List<Middleware<T, U>>.evaluate(ctx: PipelineContext<T>): MiddlewareResult<U> {
    val results = map { it.pass(ctx) }
    val failed = results.filter { it !== null }.count()
    return if (failed == 0) {
        MiddlewareResult(true, null)
    } else {
        MiddlewareResult(false, results.first()!!)
    }
}