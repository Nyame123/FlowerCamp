package com.bismark.data.network.interceptors

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.bismark.data.exception.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectivityInterceptor(private val context: Context) : Interceptor {

    private val isConnected: Boolean
        get() {
            val connectivityManager: ConnectivityManager =
                context.getSystemService(ConnectivityManager::class.java)
            val activeNetwork = connectivityManager.activeNetwork ?: return false

            val networkCapabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) -> true
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_WIFI_P2P) -> true
                else -> false
            }
        }

    override fun intercept(chain: Interceptor.Chain): Response {
        if (isConnected)
            throw NoConnectivityException
        val requestBuilder = chain.request().newBuilder()
        return chain.proceed(requestBuilder.build())
    }
}