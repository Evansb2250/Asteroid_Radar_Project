package com.example.asteroidradar

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.asteroidradar.network.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


fun estimatedMax(estimatedDiameterObject: JSONObject) =
    EstimatedDiameters(
        estimatedDiameterObject
            .getJSONObject(KILOMETERS)
            .getString(ESTIMATED_DIAMETER_MAX)
            .toDouble()
    )


fun createClosedApproachData(approachData: JSONArray): CloseApproachData {

    val closeApproachDate = approachData
        .getJSONObject(0)
        .getString(CLOSE_APPROACH_DATE)

    val speed = approachData.getJSONObject(0)
        .getJSONObject(RELATIVEVELOCITY)
        .getString(KILOMETERS_PER_SECOND).toDouble()

    val astronomical = approachData.getJSONObject(0)
        .getJSONObject(MISS_DISTANCE)
        .getString(ASTRONOMICAL).toDouble()

    return CloseApproachData(
        closeApproachDate,
        RelativeVelocity(speed),
        MissDistance(astronomical)
    )
}



/*
Takes a list of dates  and the beginning location of the JSON ELEMENT
Cycles through the dates in the Json and creates a list Asteroids based on the API call response
 */
fun allocateJsonByDate(
    dates: List<String>,
    jsonObject: JSONObject
): ArrayList<Asteroid>? {
    val listOfAsteroids = ArrayList<Asteroid>()
    //Time complexity of BIG O of (N*K)
    for (date in dates) {
        val h = mapOf<String, Any>()
        val jsonArray = jsonObject.getJSONArray(date)
        for (i in 0 until jsonArray.length()) {
            val map = getElementFromJsonArray(jsonArray.getJSONObject(i))
            val asteroid =       Asteroid(
                id = map.get(JSON_ELEMENT_ID) as Long,
                absoluteMagnitude = map.get(ABSOLUTE_MAGNITUDE) as Double,
                estimatedDiameters = map.get(ESTIMATED_DIAMETERS) as EstimatedDiameters,
                closeApproachData = map.get(CLOSE_APPROACH_DATA) as CloseApproachData,
                isHazardous = map.get(IS_POTENTIALLY_HAZARDOUS_ASTEROID) as Boolean
            )

            Log.i("APICALL", " my call ${asteroid}")

            listOfAsteroids.add(asteroid)
        }
    }
    return listOfAsteroids
}

fun getElementFromJsonArray(jsonElement: JSONObject): Map<String, Any> {
    val jsonElementMap = mutableMapOf<String, Any>()
    jsonElementMap.put(JSON_ELEMENT_ID, jsonElement.getString(JSON_ELEMENT_ID).toLong())
    jsonElementMap.put(ABSOLUTE_MAGNITUDE, jsonElement.getString(ABSOLUTE_MAGNITUDE).toDouble())
    jsonElementMap.put(IS_POTENTIALLY_HAZARDOUS_ASTEROID, jsonElement.getString(IS_POTENTIALLY_HAZARDOUS_ASTEROID).toBoolean() )
    jsonElementMap.put(ESTIMATED_DIAMETERS, estimatedMax(jsonElement.getJSONObject(ESTIMATED_DIAMETERS) ))
    jsonElementMap.put(CLOSE_APPROACH_DATA,createClosedApproachData(jsonElement.getJSONArray(CLOSE_APPROACH_DATA)) )
    return jsonElementMap
}

fun getAsteroidsFromApi(startDate: String, endDate:String, callBackResponse: MutableLiveData<Response<String>>){
    NasaApi.retrofitService.getAsteroids(startDate, endDate).enqueue(object : Callback<String> {
        override fun onResponse(call: Call<String>, response: Response<String>) { callBackResponse.value = response }
        override fun onFailure(call: Call<String>, t: Throwable) {} })
}





//request the image of the day from Nasa web service
fun getImageFromApi() : Response<String>?{
    var result: Response<String> ?= null
    NasaApi.retrofitService.getImageOfTheDay().enqueue(object : Callback<String> {
        //turn response into a string
        override fun onResponse(call: Call<String>, response: Response<String>) { result = response }
        override fun onFailure(call: Call<String>, t: Throwable) {}})
    return result
}