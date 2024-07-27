package com.bismark.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FlowerEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val price: Double,
    val url: String,
    val createdDate: Long
)