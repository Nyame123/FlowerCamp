package com.bismark.data.network

import com.bismark.data.exception.ClientException
import com.bismark.data.exception.NoConnectivityException
import com.bismark.data.exception.SessionTimeoutIOException
import com.bismark.data.network.errorConfig.NetworkErrorProcessor
import com.bismark.domain.exception.BaseException
import retrofit2.HttpException
import retrofit2.Response
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.net.ssl.SSLHandshakeException

class NetworkRouter @Inject constructor(private val networkErrorProcessor: NetworkErrorProcessor) {

    suspend fun <T> invokeApiCall(call: suspend () -> Response<T>): T? {
        var response: Response<T>? = null
        try {
            response = call.invoke()
            if (isValidResponse(response)) {
                return response.body()
            }

            val errorBody = response.errorBody()
            errorBody?.let {
                val errorModel = networkErrorProcessor.resolveErrorBody(it)
                throw networkErrorProcessor.resolveHttpExceptionOrThrowClientException(errorModel)
            }

            throw BaseException.SomethingWentWrongException

        } catch (exception: Exception) {
            throw when (exception) {
                is InterruptedIOException, is IllegalStateException, is ConnectException,
                is UnknownHostException, is NumberFormatException,
                -> BaseException.SomethingWentWrongException
                is HttpException -> networkErrorProcessor.resolveErrorCode(response?.code())
                is SSLHandshakeException -> BaseException.SSLHandshakeException
                is NoConnectivityException -> BaseException.NoConnectionException
                is SessionTimeoutIOException -> BaseException.SessionTimeoutException
                is ClientException, BaseException.SomethingWentWrongException -> exception
                else -> BaseException.BaseCustomException(exception.message.orEmpty())
            }
        }
    }

    private fun <T> isValidResponse(response: Response<T>?): Boolean {
        return response != null && response.isSuccessful && response.body() != null
    }
}