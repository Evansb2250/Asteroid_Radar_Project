package com.example.asteroidradar.network

import android.util.Log
import com.example.asteroidradar.BACK_DATE
import com.example.asteroidradar.CURRENT_DATE
import com.example.asteroidradar.DATEFORMAT
import com.example.asteroidradar.DEBUG_LOG
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*
import kotlin.collections.HashMap


private const val BASE_URL = "https://api.nasa.gov/"
private const val API_KEY = "YTP5ZlghZMp6TAxO4w6sIef1juEnEZvGSjY8hB0d"

//
//private val moshi = Moshi.Builder()
//    .add(KotlinJsonAdapterFactory())
//    .build()


private val retrofit = Retrofit.Builder()
    //tells retrofit what to do with the data
    // ScalarsConverterFactory turns the JSON file to a String
    .addConverterFactory(ScalarsConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()




interface Webservice {
    @GET("planetary/apod")
   suspend fun getImageOfTheDay(@Query("api_key") key: String = API_KEY): String

    // neo/rest/v1/feed?start_date=START_DATE&end_date=END_DATE&api_key=API_KEY
    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") key: String = API_KEY
    ): String


    // neo/rest/v1/feed?start_date=START_DATE&end_date=END_DATE&api_key=API_KEY
    @GET("neo/rest/v1/feed")
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


suspend fun getAsteroidsFromApi(): String? {

    try {
        val dates = calculateWeekSevenDaysOut()
        return NasaApi.retrofitService.getProperties(dates.get(BACK_DATE)!!,dates.get(CURRENT_DATE)!!)
    } catch (e: Exception) {

    }
    return null

}


fun calculateWeekSevenDaysOut(): HashMap<String, String> =
    HashMap<String, String>().apply {
        val last7days = Calendar.getInstance().apply { add(Calendar.DATE, -7) }.timeInMillis
        put(BACK_DATE, DATEFORMAT.format(last7days))
        val today = Calendar.getInstance().timeInMillis
        put(CURRENT_DATE, DATEFORMAT.format(today))
    }




//request the image of the day from Nasa web service
suspend fun getImageFromApi(): String? {

        try{
            val image = NasaApi.retrofitService.getImageOfTheDay()
            Log.i(DEBUG_LOG, "IMAGE  ${image.toString()}")
            return image
        }catch(e: HttpException){

        }


    return null
}



