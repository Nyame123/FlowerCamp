package com.bismark.flowercamp.di

import android.content.Context
import androidx.room.Room
import com.bismark.data.BuildConfig
import com.bismark.data.database.FlowerDatabase
import com.bismark.data.network.OkHttpClientFactory
import com.bismark.data.network.RetrofitFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideApplicationContext(@ApplicationContext context: Context): Context = context

    @Provides
    fun providesRoomDatabase(context: Context): FlowerDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = FlowerDatabase::class.java,
            name = FlowerDatabase.NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient =
        OkHttpClientFactory(context).createClient()

    @Provides
    fun providesGson(): Gson = GsonBuilder().create()

    @Provides
    fun providesGsonConverter(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Provides
    fun providesRetrofitFactory(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): RetrofitFactory =
        RetrofitFactory(
            baseUrl = BuildConfig.BASE_URL,
            okHttpClient = okHttpClient,
            gsonFactory = gsonConverterFactory
        )
}