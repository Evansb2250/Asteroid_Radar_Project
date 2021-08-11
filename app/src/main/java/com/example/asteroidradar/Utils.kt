package com.example.asteroidradar

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



//request the nested Json file from the Nasa web service
fun getAsteroidsFromApi(startDate: String, endDate:String) : Response<String>? {
    var result: Response<String>? = null
    NasaApi.retrofitService.getAsteroids(startDate, endDate).enqueue(object : Callback<String> {
        override fun onResponse(call: Call<String>, response: Response<String>) { result = response }
        override fun onFailure(call: Call<String>, t: Throwable) {} })


    return result
}


fun allocateJsonByDate(
    dates: List<String>,
    jsonObject: JSONObject
): ArrayList<Asteroid>? {
    val listOfAsteroids = ArrayList<Asteroid>()
    for (date in dates) {
        val jsonArray = jsonObject.getJSONArray(date)
        for (i in 0 until jsonArray.length()) {
            val jsonElement = jsonArray.getJSONObject(i)
            val id = jsonElement.getString(JSON_ELEMENT_ID).toLong()
            val absoluteMagnitude = jsonElement.getString(ABSOLUTE_MAGNITUDE).toDouble()
            val isHazardous =
                jsonElement.getString(IS_POTENTIALLY_HAZARDOUS_ASTEROID).toBoolean()

            val estimatedDiameterInstance =
                estimatedMax(jsonElement.getJSONObject(ESTIMATED_DIAMETERS))
            val closeApproachInstance =
                createClosedApproachData(jsonElement.getJSONArray(CLOSE_APPROACH_DATA))

            listOfAsteroids.add(
                Asteroid(
                    id = id,
                    absoluteMagnitude = absoluteMagnitude,
                    estimatedDiameters = estimatedDiameterInstance,
                    closeApproachData = closeApproachInstance,
                    isHazardous = isHazardous
                )
            )
        }
    }
    return listOfAsteroids
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