package io.kida.ktor.middleware

import io.kida.ktor.middleware.model.MiddlewareResult
import org.jetbrains.ktor.pipeline.PipelineContext

interface Middleware<T: Any, out U: Any> {
    suspend fun pass(ctx: PipelineContext<T>): U?
}

class Middlewares {
    companion object {
        suspend fun <T: Any, U: Any>evaluate(ctx: PipelineContext<T>, middlewares: List<Middleware<T, U>>): MiddlewareResult<U> {
            val results = middlewares.map { it.pass(ctx) }
            val failed = results.filter { it !== null }.count()
            return if (failed == 0) {
                MiddlewareResult(true, null)
            } else {
                MiddlewareResult(false, results.first()!!)
            }
        }
    }
}