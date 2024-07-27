package com.bismark.flowercamp.di

import com.bismark.data.network.errorConfig.NetworkErrorProcessor
import com.bismark.data.network.errorConfig.NetworkErrorProcessorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface DataBindModule {

    @Binds
    fun bindsNetworkErrorProcessor(networkErrorProcessorImpl: NetworkErrorProcessorImpl): NetworkErrorProcessor

}