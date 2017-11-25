package io.kida.ktor.middleware

import io.kida.ktor.middleware.model.HttpStatus
import org.jetbrains.ktor.pipeline.PipelineContext

class BearerAuthentication(private val token: String) : Middleware<Unit, HttpStatus> {
    suspend override fun pass(ctx: PipelineContext<Unit>): HttpStatus? {
        if (ctx.call.request.headers["Authorization"].equals("Bearer $token")) {
            return null
        }
        return HttpStatus(401, "unauthorized")
    }
}