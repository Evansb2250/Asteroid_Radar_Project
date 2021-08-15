package com.example.asteroidradar.repository

import com.example.asteroidradar.database.AsteroidDatabase
import com.example.asteroidradar.jsonParser
import com.example.asteroidradar.network.NasaApi
import com.example.asteroidradar.network.getAsteroidsFromApi
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import org.json.JSONObject
import org.json.JSONTokener

class Repository(val database:AsteroidDatabase) {


//    suspend fun refreshAsteroidsFromNetwork(){
//        //use a network call to gather information from the api
//        withContext(Dispatcher.IO){
//
//        }
//    }

}