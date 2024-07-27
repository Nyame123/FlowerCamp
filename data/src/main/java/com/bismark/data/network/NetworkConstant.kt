package com.bismark.data.network

object NetworkConstant {
    enum class HTTPStatusCode(val code: Int) {
        // Error codes
        UNAUTHORIZED(401),
        FORBIDDEN(403),
        CONNECTION_TIMEOUT(408),
        NOT_FOUND(404),

        // Server errors
        INTERNAL_SERVER_ERROR(500),
        BAD_GATEWAY(502),
        SERVICE_UNAVAILABLE(503),
        GATEWAY_TIMEOUT(504),
    }
}