package com.example.asteroidradar.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.asteroidradar.DATEFORMAT
import com.example.asteroidradar.domain.Asteroid
import java.util.*


@Entity(tableName = "Asteroids_table")
data class DatabaseAsteroid(
    @PrimaryKey(autoGenerate = false)
    var id: Long= 0L,
    var name: String,
    var approach_date: Long = 0L,
    var absolute_magnitude:Double = 0.0,
    var estimated_diameter_max:Double = 0.0,
    var hazardous: Boolean = false,
    var kilometers_per_second:Double = 0.0,
    var astronomical_value:Double= 0.0)

fun List<DatabaseAsteroid>.asDomainModel():List<Asteroid>{
    return map {
        Asteroid(
            id = it.id,
            name= it.name,
            approachDate = DATEFORMAT.format(Date(it.approach_date)),
            absoluteMagnitude = it.absolute_magnitude,
            estimatedDiameterMax = it.estimated_diameter_max,
            isHazardous = it.hazardous,
            kilometersPerSecond = it.kilometers_per_second,
            astronomical = it.astronomical_value
            )
    }
}



@Entity(tableName = "ImageOfTheDay_table")
data class DatabaseImageOfTheDay(
    @PrimaryKey
    var date:Long,
    var url:String,
    var mediaType:String,
    var title:String,
    var explanation:String,
)
