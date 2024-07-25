package com.bismark.flowercamp.di

import android.content.Context
import androidx.room.Room
import com.bismark.data.database.FlowerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun providesRoomDatabase(@ApplicationContext context: Context): FlowerDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = FlowerDatabase::class.java,
            name = FlowerDatabase.NAME
        ).fallbackToDestructiveMigration().build()
    }
}