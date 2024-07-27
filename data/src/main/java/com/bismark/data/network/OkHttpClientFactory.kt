package com.bismark.data.network

import android.content.Context
import com.bismark.data.BuildConfig
import com.bismark.data.network.interceptors.ChuckerInterceptorFactory
import com.bismark.data.network.interceptors.NetworkConnectivityInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class OkHttpClientFactory(val context: Context) {

    private companion object {
        const val WAIT_TIME = 300L
    }

    fun createClient(): OkHttpClient {
        val networkConnectivityInterceptor = NetworkConnectivityInterceptor(context)
        val chuckerInterceptorFactory = ChuckerInterceptorFactory(context)
        val loggingInterceptor = HttpLoggingInterceptor().also {
            it.level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC else HttpLoggingInterceptor.Level.NONE
        }
        return OkHttpClient.Builder()
            .addInterceptor(networkConnectivityInterceptor)
            .addInterceptor(chuckerInterceptorFactory.create())
            .addInterceptor(loggingInterceptor)
            .writeTimeout(WAIT_TIME, TimeUnit.SECONDS)
            .readTimeout(WAIT_TIME, TimeUnit.SECONDS)
            .connectTimeout(WAIT_TIME, TimeUnit.SECONDS)
            .build()
    }
}