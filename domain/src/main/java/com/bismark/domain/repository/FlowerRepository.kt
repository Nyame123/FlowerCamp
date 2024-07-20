package com.bismark.domain.repository

import com.bismark.domain.FlowerDomainModel

interface FlowerRepository {
    fun getAllFlowers(): List<FlowerDomainModel>
    fun getFlower(id: Long): FlowerDomainModel
    fun deleteFlower(id: Long): Long
}