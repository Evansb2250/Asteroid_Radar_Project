package com.example.asteroidradar.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Asteroids_table")
data class Asteroid(
    @PrimaryKey(autoGenerate = false)
    var id: Long= 0L,
    var approach_date: Long = 0L,
    var absolute_magnitude:Double = 0.0,
    var estimated_diameter_max:Double = 0.0,
    var hazardous: Boolean = false,
    var kilometers_per_second:Double = 0.0,
    var astronomical_value:Double= 0.0)


@Entity(tableName = "ImageOfTheDay_table")
data class ImageOfTheDay(
    @PrimaryKey
    var url:String,
    var mediaType:String,
    var title:String
)