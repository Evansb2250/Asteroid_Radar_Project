package com.example.asteroidradar.network

import kotlinx.serialization.*


data class DataTransferObject(
    var id: Long? = -1L,
    var absoluteMagnitude: Double? = -1.0,
    var estimatedDiameterMax: Double? = -1.0,
    var isHazardous: Boolean?,
    var approachDate: String?,
    val kilometersPerSecond: Double? = -1.0,
    val astronomical: Double? = -1.0,

//
)


data class Asteroid(
    var id: Long,
    var absoluteMagnitude: Double,
    var estimatedDiameters: EstimatedDiameters,
    var closeApproachData: CloseApproachData,
    var isHazardous: Boolean,
) {
}

data class EstimatedDiameters(val estimatedDiameterMax: Double)


data class CloseApproachData(
    val date: String,
    var relativeVelocity: RelativeVelocity,
    var missDistance: MissDistance
)

data class RelativeVelocity(val kilometersPerSecond: Double)
data class MissDistance(val astronomical: Double)
