package com.bismark.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bismark.data.FlowerEntity

@Dao
interface FlowerDao{

    @Query("Select * from FlowerEntity where id = :id")
    suspend fun getFlowerById(id: Long): FlowerEntity

    @Query("Select * from FlowerEntity")
    suspend fun getFlowerById(): List<FlowerEntity>

    @Update
    suspend fun updateFlower(vararg flowerEntity: FlowerEntity): Int

    @Insert
    suspend fun insertFlower(vararg flowerEntity: FlowerEntity): List<Long>

    @Delete
    suspend fun deleteFlower()
}
