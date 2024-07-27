package com.bismark.data.network.errorConfig

import com.bismark.domain.exception.BaseException
import okhttp3.ResponseBody

interface NetworkErrorProcessor{

    @Throws(Exception::class)
    fun resolveErrorBody(errorBody: ResponseBody): ErrorResponseModel
    fun resolveErrorCode(errorCode: Int?): BaseException
    fun resolveHttpExceptionOrThrowClientException(errorModel: ErrorResponseModel): Exception
}