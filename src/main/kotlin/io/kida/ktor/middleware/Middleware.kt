package io.kida.ktor.middleware

import org.jetbrains.ktor.pipeline.PipelineContext

interface Middleware<T: Any, out U: Any> {
    suspend fun pass(ctx: PipelineContext<T>): U?
}

class Middlewares {
    companion object {
        suspend fun <T: Any, U: Any>evaluate(ctx: PipelineContext<T>, middlewares: List<Middleware<T, U>>, success: suspend () -> Unit, failure: suspend (status: U) -> Unit) {
            val results = middlewares.map { it.pass(ctx) }
            val failed = results.filter { it !== null }.count()
            if (failed == 0) {
                success()
            } else {
                failure(results.first()!!)
            }
        }
    }
}