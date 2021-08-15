package com.example.asteroidradar.repository

import com.example.asteroidradar.database.AsteroidDatabase
import com.example.asteroidradar.jsonParser
import com.example.asteroidradar.network.getAsteroidsFromApi
import com.example.asteroidradar.network.toDatabaseDomain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import org.json.JSONObject
import org.json.JSONTokener

class Repository(val database: AsteroidDatabase) {


    suspend fun refreshAsteroidsFromNetwork() {
        //use a network call to gather information from the api
        CoroutineScope(Dispatchers.IO).launch {
                database.asteroidDao().insertAllAsteroids(jsonParser().toDatabaseDomain())
            }
        }
    }


