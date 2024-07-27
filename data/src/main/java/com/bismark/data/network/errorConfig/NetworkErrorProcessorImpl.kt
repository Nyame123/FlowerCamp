package com.bismark.data.network.errorConfig

import com.bismark.data.exception.ClientException
import com.bismark.data.network.NetworkConstant
import com.bismark.domain.exception.BaseException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import javax.inject.Inject

class NetworkErrorProcessorImpl @Inject constructor(private val gson: Gson) :
    NetworkErrorProcessor {
    override fun resolveErrorBody(errorBody: ResponseBody): ErrorResponseModel {
        val typeToken = object : TypeToken<ErrorResponseModel>() {}.type
        return gson.fromJson(errorBody.charStream(), typeToken)
    }

    override fun resolveErrorCode(errorCode: Int?): BaseException {
        return when (errorCode) {
            NetworkConstant.HTTPStatusCode.UNAUTHORIZED.code,
            NetworkConstant.HTTPStatusCode.FORBIDDEN.code,
            -> BaseException.SessionTimeoutException

            NetworkConstant.HTTPStatusCode.CONNECTION_TIMEOUT.code -> BaseException.ServiceUnavailable
            NetworkConstant.HTTPStatusCode.INTERNAL_SERVER_ERROR.code,
            NetworkConstant.HTTPStatusCode.BAD_GATEWAY.code,
            NetworkConstant.HTTPStatusCode.SERVICE_UNAVAILABLE.code,
            NetworkConstant.HTTPStatusCode.GATEWAY_TIMEOUT.code,
            -> BaseException.SomethingWentWrongException

            else -> BaseException.SomethingWentWrongException
        }
    }

    override fun resolveHttpExceptionOrThrowClientException(errorModel: ErrorResponseModel): Exception {
        return when (errorModel.errorCode) {
            NetworkConstant.HTTPStatusCode.UNAUTHORIZED.code.toString(),
            NetworkConstant.HTTPStatusCode.FORBIDDEN.code.toString(),
            -> BaseException.SessionTimeoutException

            NetworkConstant.HTTPStatusCode.CONNECTION_TIMEOUT.code.toString(),
            NetworkConstant.HTTPStatusCode.SERVICE_UNAVAILABLE.code.toString(),
            -> BaseException.ServiceUnavailable
            NetworkConstant.HTTPStatusCode.INTERNAL_SERVER_ERROR.code.toString(),
            NetworkConstant.HTTPStatusCode.BAD_GATEWAY.code.toString(),
            NetworkConstant.HTTPStatusCode.GATEWAY_TIMEOUT.code.toString(),
            -> BaseException.SomethingWentWrongException

            else ->
                ClientException(
                    errorCode = errorModel.errorCode,
                    message = errorModel.message,
                )
        }
    }
}