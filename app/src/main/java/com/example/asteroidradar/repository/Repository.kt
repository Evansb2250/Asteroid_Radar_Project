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
import retrofit2.HttpException
import java.net.UnknownHostException

class Repository(val database: AsteroidDatabase) {

    val asteroids: LiveData<List<Asteroid>> = Transformations.map(database.asteroidDao().getAsteroids()) {
            it.asDomainModel()
        }
    val imageOfTheDay:LiveData<ImageOfTheDay?> = Transformations.map(database.imageDao().getCurrentImage()){
       it?.let {ImageDatabaseModelToDomain(it) }  }



    suspend fun refreshData(){
        //e Unknown Host Exception prevents the app from crashing if there is no
        // internet connection.
        try{
            refreshAsteroidsFromNetwork()
            refreshImageOfTheDay()
        }catch (e: UnknownHostException){

        }

    }


    private suspend fun refreshAsteroidsFromNetwork() {
        Log.i(DEBUG_LOG, " in the refreshAsteroid")
        //use a network call to gather information from the api
        withContext(Dispatchers.IO) {
            getJSonObject()?.let {
                    database.asteroidDao().insertAllAsteroids(jsonParser(it).toDatabaseDomain())
            }
        }
    }


    private suspend fun refreshImageOfTheDay(){
        val format = Json { ignoreUnknownKeys = true }
        withContext(Dispatchers.IO) {
            getImageFromApi()?.let {
                val imageObject =  format.decodeFromString<ImageOfTheDayDTO>(it)
                database.imageDao().insertImage(ImagetoDatabaseModel(imageObject))
            }
        }
    }
}


private suspend fun getJSonObject(): JSONObject? {
    try {
         getAsteroidsFromApi()?.let {
             return JSONTokener(it).nextValue() as JSONObject
         }
    } catch (e: HttpException) {

    }
    return null
}