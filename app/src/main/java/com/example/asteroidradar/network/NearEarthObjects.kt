package com.example.asteroidradar.network

import com.google.gson.annotations.SerializedName


data class NearEarthObjects(
    @SerializedName("id")
    val id:Int,
    @SerializedName("absolute_magnitude_h")
    val absoluteMagnitude:Double,
    @SerializedName("estimated_diameter_max")
            val estimated_diameter_max:Double,
    @SerializedName("is_potentially_hazardous_asteroid")
    val is_potentially_hazardous_asteroid : Boolean) {
}


data class CloseApproachData(val kilometers_per_second: Double, val astronomical : Double )