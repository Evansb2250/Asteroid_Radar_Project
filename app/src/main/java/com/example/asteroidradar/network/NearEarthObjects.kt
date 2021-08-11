package com.example.asteroidradar.network
import kotlinx.serialization.*




data class Asteroid(
    var id:Long,
    var absoluteMagnitude: Double,
    var estimatedDiameters: EstimatedDiameters,
    var closeApproachData: CloseApproachData,
    var isHazardous:Boolean,
    ) {
}

data class EstimatedDiameters(val estimatedDiameterMax : Double )


data class CloseApproachData(val date:String ,var relativeVelocity: RelativeVelocity, var missDistance: MissDistance)
data class RelativeVelocity(val kilometersPerSecond: Double )
data class MissDistance(val astronomical: Double )
