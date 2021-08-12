package com.example.asteroidradar.network

import kotlinx.serialization.*


data class DataTransferObject(
    var id: Long? = -1L,
    var absoluteMagnitude: Double? = -1.0,
    var estimatedDiameterMax: Double? = -1.0,
    var isHazardous: Boolean?,
    var approachDate: String?,
    val kilometersPerSecond: Double? = -1.0,
    val astronomical: Double? = -1.0
)

