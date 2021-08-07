package com.example.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AsteroidDao {


    //Insert a list of asteroids, onConflict strategy is set to replace duplicate id's
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsteroid(asteroids: Asteroid)

    //Insert a list of asteroids, onConflict strategy is set to replace duplicate id's
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAsteroids(asteroids: List<Asteroid>)


    //retrieves a specific asteroid
    @Query("Select * From asteroids_table Where id =:key")
    suspend fun getAsteroid(key: Int): Asteroid?


    //Updates the state of the asteroids
    @Update
    suspend fun update(asteroids: List<Asteroid>)


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