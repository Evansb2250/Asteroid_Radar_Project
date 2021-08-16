package com.example.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AsteroidDao {


    //Insert a list of asteroids, onConflict strategy is set to replace duplicate id's
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsteroid(asteroids: DatabaseAsteroid)

    //Insert a list of asteroids, onConflict strategy is set to replace duplicate id's
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAsteroids(databaseAsteroids: List<DatabaseAsteroid>)


    //retrieves a specific asteroid
    @Query("Select * From asteroids_table Order by approach_date DESC")
     fun getAsteroid(): LiveData<List<DatabaseAsteroid>>


    //Updates the state of the asteroids
    @Update
    suspend fun update(databaseAsteroids: List<DatabaseAsteroid>)


    //removes all the rows from the Asteroid table
    @Query("DELETE FROM Asteroids_table")
    suspend fun clear()

}

//
//
//@Dao
//interface ImageOfTheDayDao{
//
//    @Insert
//    suspend fun insertImage(image:ImageOfTheDay) // adds an image
//
//    @Update
//    suspend fun updateImage(image: ImageOfTheDay) //updates pre-existing images
//
//    @Query("DELETE From imageoftheday_table")
//    suspend fun clear() // removes all images from the database
//
//
//}