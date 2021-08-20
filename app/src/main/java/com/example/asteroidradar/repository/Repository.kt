package com.example.asteroidradar.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.asteroidradar.DEBUG_LOG
import com.example.asteroidradar.ImageDatabaseModelToDomain
import com.example.asteroidradar.ImagetoDatabaseModel
import com.example.asteroidradar.database.AsteroidDatabase
import com.example.asteroidradar.database.asDomainModel
import com.example.asteroidradar.domain.Asteroid
import com.example.asteroidradar.domain.ImageOfTheDay
import com.example.asteroidradar.jsonParser
import com.example.asteroidradar.network.ImageOfTheDayDTO
import com.example.asteroidradar.network.getAsteroidsFromApi
import com.example.asteroidradar.network.getImageFromApi
import com.example.asteroidradar.network.toDatabaseDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.json.JSONObject
import org.json.JSONTokener

class Repository(val database: AsteroidDatabase) {

    val asteroids: LiveData<List<Asteroid>> =
        Transformations.map(database.asteroidDao().getAsteroids()) {
            it.asDomainModel()
        }
    val imageOfTheDay: LiveData<ImageOfTheDay?> =
        Transformations.map(database.imageDao().getCurrentImage()) {
            it?.let { ImageDatabaseModelToDomain(it) }
        }



    suspend fun refreshAsteroidsFromNetwork() {
        Log.i(DEBUG_LOG, " in the refreshAsteroid")
        //use a network call to gather information from the api
        withContext(Dispatchers.IO) {
          do {
              val result = getAsteroidsFromApi()?.let {
                  val jsonObject = JSONTokener(it).nextValue() as JSONObject
                  database.asteroidDao()
                      .insertAllAsteroids(jsonParser(jsonObject).toDatabaseDomain())
              }
          }while(result == null)

        }
    }


    suspend fun refreshImageOfTheDay() {
        val format = Json { ignoreUnknownKeys = true }
        withContext(Dispatchers.IO) {
           do {
             val result =  getImageFromApi()?.let {
                   val imageObject = format.decodeFromString<ImageOfTheDayDTO>(it)
                   database.imageDao().insertImage(ImagetoDatabaseModel(imageObject))
               }
           }while (result == null)
        }
    }
}
