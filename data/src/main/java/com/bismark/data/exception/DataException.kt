package com.bismark.data.exception

import java.io.IOException

object NoConnectivityException : IOException()
internal object SessionTimeoutIOException : IOException()
internal data class ClientException(val errorCode: String?, override val message: String?) : Exception()