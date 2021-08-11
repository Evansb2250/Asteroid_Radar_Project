package com.example.asteroidradar

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.asteroidradar.database.Asteroid
import org.json.JSONObject


class TestUtil {

    companion object {
        fun createAsteroid(
            id: Long,
            approach_date: Long,
            absolute_magnitude: Double,
            estimated_diameter_max: Double,
            hazardous: Boolean,
            kilometers_per_second: Double,
            astronomical_value: Double
        ): Asteroid {

            return Asteroid(
                id,
                approach_date,
                absolute_magnitude,
                estimated_diameter_max,
                hazardous,
                kilometers_per_second,
                astronomical_value
            )
        }
    }
}


