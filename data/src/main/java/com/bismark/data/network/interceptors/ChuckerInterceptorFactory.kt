package com.bismark.data.network.interceptors

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor

class ChuckerInterceptorFactory(private val context: Context) {
    private companion object {
        const val CONTENT_LENGTH = 250000L
    }

    fun create(): ChuckerInterceptor = ChuckerInterceptor.Builder(context)
        .collector(ChuckerCollector(context))
        .maxContentLength(CONTENT_LENGTH)
        .redactHeaders(emptySet())
        .alwaysReadResponseBody(true)
        .build()
}