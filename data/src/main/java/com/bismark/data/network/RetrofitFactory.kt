package com.bismark.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitFactory @Inject constructor(
    private val baseUrl: String,
    private val okHttpClient: OkHttpClient,
    private val gsonFactory: GsonConverterFactory
) {

    private companion object {
        var retrofit: Retrofit? = null
    }

    private fun init(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonFactory)
            .client(okHttpClient)
            .build()
    }

    fun <T> create(apiService: Class<T>): T {
        if (retrofit == null)
            retrofit = init()
        return retrofit!!.create(apiService)
    }
}