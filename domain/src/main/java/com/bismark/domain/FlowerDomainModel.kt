package com.bismark.domain

import java.math.BigDecimal

data class FlowerDomainModel(
    private val id: Long,
    private val name: String,
    private val description: String,
    private val url: String,
    private val price: BigDecimal,
    private val createdDate: Long,
)
