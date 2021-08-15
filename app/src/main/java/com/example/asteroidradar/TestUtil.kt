package com.example.asteroidradar

import com.example.asteroidradar.database.DatabaseAsteroid


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
        ): DatabaseAsteroid {

            return DatabaseAsteroid(
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


