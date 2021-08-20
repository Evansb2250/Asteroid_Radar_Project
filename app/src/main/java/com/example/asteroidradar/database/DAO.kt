package com.example.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AsteroidDao {

    //Insert a list of asteroids, onConflict strategy is set to replace duplicate id's
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAsteroids( databaseAsteroids: List<DatabaseAsteroid>)


    //retrieves a specific asteroid
    @Query("Select * From asteroids_table Order by approach_date DESC")
     fun getAsteroids(): LiveData<List<DatabaseAsteroid>>


    //Updates the state of the asteroids
    @Update
    suspend fun update(databaseAsteroids: List<DatabaseAsteroid>)


    //removes all the rows from the Asteroid table
    @Query("DELETE FROM Asteroids_table")
    suspend fun clear()
}

//

@Dao
interface ImageOfTheDayDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(image:DatabaseImageOfTheDay) // adds an image

    @Query("SELECT * FROM imageoftheday_table ORDER BY date DESC LIMIT 1")
     fun getCurrentImage():LiveData<DatabaseImageOfTheDay?>

    @Query("DELETE From imageoftheday_table")
    suspend fun clear() // removes all images from the database


}