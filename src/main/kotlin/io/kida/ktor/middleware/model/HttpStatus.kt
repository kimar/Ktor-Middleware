package io.kida.ktor.middleware.model

data class HttpStatus(val status: Int, val message: String, val details: Any? = null) {
    companion object {
        fun OK(details: Any? = null): HttpStatus = HttpStatus(200, "ok", details)
    }
}