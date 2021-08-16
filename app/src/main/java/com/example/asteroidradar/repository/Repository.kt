package com.example.asteroidradar.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.asteroidradar.DEBUG_LOG
import com.example.asteroidradar.database.AsteroidDatabase
import com.example.asteroidradar.database.asDomainModel
import com.example.asteroidradar.domain.Asteroid
import com.example.asteroidradar.jsonParser
import com.example.asteroidradar.network.getAsteroidsFromApi
import com.example.asteroidradar.network.toDatabaseDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.HttpException

class Repository(val database: AsteroidDatabase) {

    val asteroids: LiveData<List<Asteroid>> = Transformations.map(database.asteroidDao().getAsteroid()) {
            it.asDomainModel()
        }

    suspend fun refreshAsteroidsFromNetwork() {
        Log.i(DEBUG_LOG, " in the refreshAsteroid")
        //use a network call to gather information from the api
        withContext(Dispatchers.IO) {
            getJSonObject()?.let {
                    database.asteroidDao().insertAllAsteroids(jsonParser(it).toDatabaseDomain())
            }
        }
    }
}


suspend fun getJSonObject(): JSONObject? {
    try {
         getAsteroidsFromApi()?.let {
             return JSONTokener(it).nextValue() as JSONObject
         }
    } catch (e: HttpException) {

    }
    return null
}