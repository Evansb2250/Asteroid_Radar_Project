package com.example.asteroidradar.network

import com.example.asteroidradar.DATEFORMAT
import com.example.asteroidradar.database.DatabaseAsteroid
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



data class AsteroidDTO(
    var id: Long = -1L,
    var absoluteMagnitude: Double = -1.0,
    var estimatedDiameterMax: Double = -1.0,
    var isHazardous: Boolean,
    var approachDate: String,
    val kilometersPerSecond: Double = -1.0,
    val astronomical: Double = -1.0
)


fun ArrayList<AsteroidDTO>.toDatabaseDomain():List<DatabaseAsteroid>{
    return map {
        DatabaseAsteroid(
            id = it.id,
            absolute_magnitude = it.absoluteMagnitude,
            estimated_diameter_max = it.estimatedDiameterMax,
            hazardous = it.isHazardous,
            approach_date = DATEFORMAT.parse(it.approachDate).time,
            kilometers_per_second = it.kilometersPerSecond,
            astronomical_value = it.astronomical
        )
    }
}



//
@Serializable
data class ImageOfTheDayDTO(
    @SerialName("media_type")var mediaType:String,
    @SerialName("title")var title:String,
    @SerialName("explanation") var explanation:String,
    @SerialName("date") var date:String,
    @SerialName("url")var url:String,
)






