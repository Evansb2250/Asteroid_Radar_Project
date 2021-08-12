package com.example.asteroidradar.network

import android.content.Context
import androidx.annotation.RestrictTo
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.lang.Exception

//"https://api.nasa.gov/neo/rest/v1/feed?start_date=START_DATE&end_date=END_DATE&api_key=API_KEY


private const val BASE_URL = "https://api.nasa.gov/"
private const val API_KEY = "YTP5ZlghZMp6TAxO4w6sIef1juEnEZvGSjY8hB0d"


private val retrofit = Retrofit.Builder()
    //tells retrofit what to do with the data
    // ScalarsConverterFactory turns the JSON file to a String
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


interface Webservice {
    @GET("planetary/apod?")
    fun getImageOfTheDay(@Query("api_key") key: String = API_KEY): Call<String>

    // neo/rest/v1/feed?start_date=START_DATE&end_date=END_DATE&api_key=API_KEY
    @GET("neo/rest/v1/feed?")
    suspend fun getAsteroids(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") key: String = API_KEY
    ): String


    // neo/rest/v1/feed?start_date=START_DATE&end_date=END_DATE&api_key=API_KEY
    @GET("neo/rest/v1/feed?")
    suspend fun getProperties(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") key: String = API_KEY
    ): String

}


object NasaApi {
    val retrofitService: Webservice by lazy {
        retrofit.create(Webservice::class.java)
    }
}


suspend fun getAsteroidsFromApi(
    startDate: String,
    endDate: String
): String? {

    try {
        return NasaApi.retrofitService.getProperties(startDate, endDate)
    } catch (e: Exception) {

    }
    return null

}


//request the image of the day from Nasa web service
fun getImageFromApi(): Response<String>? {
    var result: Response<String>? = null
    NasaApi.retrofitService.getImageOfTheDay().enqueue(object : Callback<String> {
        //turn response into a string
        override fun onResponse(call: Call<String>, response: Response<String>) {
            result = response
        }

        override fun onFailure(call: Call<String>, t: Throwable) {}
    })
    return result
}


