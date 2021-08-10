package com.example.asteroidradar.network
import kotlinx.serialization.*









@Serializable
data class TransferDomainObjects(
    @SerialName( "near_earth_objects")
    val nearEarthObject: NearEarthObjects) {
}

@Serializable
data class NearEarthObjects(
    val date:String?,
    @SerialName("absolute_magnitude_h")
    val absoluteMagniture: Double
    ) {
}


