package com.example.asteroidradar.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "Asteroids")
data class DatabaseEntities(
    @PrimaryKey(autoGenerate = false) val id: Long,
    @ColumnInfo(name = "approach_date") val approach_date: Date,
    @ColumnInfo(name = "absolute_magnitude") val absolute_magnitude:Double,
    @ColumnInfo(name = "estimated_diameter_max")val estimated_diameter_max:Double,
    @ColumnInfo(name = "is_potentialy_hazardous_asteroid") val boolean: Boolean,
    @ColumnInfo(name = "kilometers_per_second") val kilometers_per_second:Double,
    @ColumnInfo(name = "astronomical_value") val astronomical_value:Double)
