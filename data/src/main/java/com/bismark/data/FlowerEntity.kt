package com.bismark.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FlowerEntity(
    @PrimaryKey
    private val id: Long,
    private val name: String,
    private val price: Double,
    private val url: String,
    private val createdDate: Long
)