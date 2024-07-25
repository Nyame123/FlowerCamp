package com.bismark.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bismark.data.FlowerEntity
import com.bismark.data.dao.FlowerDao

@Database(entities = [FlowerEntity::class], version = FlowerDatabase.VERSION, exportSchema = true)
abstract class FlowerDatabase : RoomDatabase() {

    companion object {
        const val VERSION = 1
        const val NAME = "flowers"
    }

    abstract fun getFlowerDao(): FlowerDao

}