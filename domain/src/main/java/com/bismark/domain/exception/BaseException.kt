package com.bismark.domain.exception

sealed class BaseException(override val message: String = "") : Exception(message) {
    object SomethingWentWrongException : BaseException()
    object ServiceUnavailable : BaseException()
    object SessionTimeoutException : BaseException()
    object SSLHandshakeException : BaseException()
    object NoConnectionException : BaseException()
    class BaseCustomException(override val message: String) : BaseException()

}