package com.example.asteroidradar.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Asteroids_table")
data class Asteroid(
    @PrimaryKey(autoGenerate = false)
    var id: Long= 0L,
    @ColumnInfo(name = "approach_date")
    var approach_date: Long = 0L,
    @ColumnInfo(name = "absolute_magnitude")
    var absolute_magnitude:Double = 0.0,
    @ColumnInfo(name = "estimated_diameter_max")
    var estimated_diameter_max:Double = 0.0,
    @ColumnInfo(name = "is_potentialy_hazardous_asteroid")
    var hazardous: Boolean = false,
    @ColumnInfo(name = "kilometers_per_second")
    var kilometers_per_second:Double = 0.0,
    @ColumnInfo(name = "astronomical_value")
    var astronomical_value:Double= 0.0)


@Entity(tableName = "ImageOfTheDay_table")
data class ImageOfTheDay(
    @PrimaryKey
    var url:String,
    var mediaType:String,
    var title:String
)