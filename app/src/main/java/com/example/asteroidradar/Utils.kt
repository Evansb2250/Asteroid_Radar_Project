package com.example.asteroidradar

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.asteroidradar.network.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
Takes a list of dates  and the beginning location of the JSON ELEMENT
Cycles through the dates in the Json and creates a list Asteroids based on the API call response
 */
fun jsonParser(
    dates: List<String>,
    jsonObject: JSONObject
): ArrayList<DataTransferObject>? {
    val listOfAsteroids = ArrayList<DataTransferObject>()
    //Time complexity of BIG O of (N*K)
    for (date in dates) {
        val jsonArray = jsonObject.getJSONArray(date)
        for (i in 0 until jsonArray.length()) {
            val transferObject = DataTransferObject(
                jsonArray.getJSONObject(i).getString(JSON_ELEMENT_ID).toLong(),
                jsonArray.getJSONObject(i).getString(ABSOLUTE_MAGNITUDE).toDouble(),
                jsonArray.getJSONObject(i).getJSONObject(ESTIMATED_DIAMETERS).getJSONObject(KILOMETERS).getString(ESTIMATED_DIAMETER_MAX).toDouble(),
                jsonArray.getJSONObject(i).getString(IS_POTENTIALLY_HAZARDOUS_ASTEROID).toBoolean(),
                jsonArray.getJSONObject(i).getJSONArray(CLOSE_APPROACH_DATA).getJSONObject(0).getString(CLOSE_APPROACH_DATE),
                jsonArray.getJSONObject(i).getJSONArray(CLOSE_APPROACH_DATA).getJSONObject(0).getJSONObject(RELATIVEVELOCITY).getString(KILOMETERS_PER_SECOND).toDouble(),
                jsonArray.getJSONObject(i).getJSONArray(CLOSE_APPROACH_DATA).getJSONObject(0).getJSONObject(MISS_DISTANCE).getString(ASTRONOMICAL).toDouble()
            )
            listOfAsteroids.add(transferObject)
            Log.i("RESULtS", transferObject.toString())
        }
    }
    Log.i("RESULtS", "${listOfAsteroids.size}")
    return listOfAsteroids
}













///*
//Takes a list of dates  and the beginning location of the JSON ELEMENT
//Cycles through the dates in the Json and creates a list Asteroids based on the API call response
// */
//fun allocateJsonByDate(
//    dates: List<String>,
//    jsonObject: JSONObject
//): ArrayList<Asteroid>? {
//    val listOfAsteroids = ArrayList<Asteroid>()
//    //Time complexity of BIG O of (N*K)
//    for (date in dates) {
//        val jsonArray = jsonObject.getJSONArray(date)
//        for (i in 0 until jsonArray.length()) {
//            addToList(listOfAsteroids,  setElementsFromJsonArrayToMap(jsonArray.getJSONObject(i), hashMapOf<String, Any>()))
//        }
//    }
//
//    Log.i("APICALL", "${listOfAsteroids.size}")
//    return listOfAsteroids
//}
//
//
//private fun addToList(listOfAsteroids: ArrayList<Asteroid>, map: Map<String, Any>) {
//    listOfAsteroids.add(
//        Asteroid(
//            id = map.get(JSON_ELEMENT_ID) as Long,
//            absoluteMagnitude = map.get(ABSOLUTE_MAGNITUDE) as Double,
//            estimatedDiameters = map.get(ESTIMATED_DIAMETERS) as EstimatedDiameters,
//            closeApproachData = map.get(CLOSE_APPROACH_DATA) as CloseApproachData,
//            isHazardous = map.get(IS_POTENTIALLY_HAZARDOUS_ASTEROID) as Boolean
//        )
//    )
//}
//
//private fun setElementsFromJsonArrayToMap(jsonElement: JSONObject, jsonElementMap: MutableMap<String, Any>): Map<String, Any> {
//    jsonElementMap.put(JSON_ELEMENT_ID, jsonElement.getString(JSON_ELEMENT_ID).toLong())
//    jsonElementMap.put(ABSOLUTE_MAGNITUDE, jsonElement.getString(ABSOLUTE_MAGNITUDE).toDouble())
//    jsonElementMap.put(IS_POTENTIALLY_HAZARDOUS_ASTEROID, jsonElement.getString(IS_POTENTIALLY_HAZARDOUS_ASTEROID).toBoolean())
//    jsonElementMap.put(ESTIMATED_DIAMETERS, estimatedMax(jsonElement.getJSONObject(ESTIMATED_DIAMETERS)))
//    jsonElementMap.put(CLOSE_APPROACH_DATA, createClosedApproachData(jsonElement.getJSONArray(CLOSE_APPROACH_DATA)))
//    return jsonElementMap
//}
//
//private fun estimatedMax(estimatedDiameterObject: JSONObject) =
//    EstimatedDiameters(estimatedDiameterObject.getJSONObject(KILOMETERS).getString(ESTIMATED_DIAMETER_MAX).toDouble())
//
//
//private fun createClosedApproachData(approachData: JSONArray): CloseApproachData {
//
//    val closeApproachDate = approachData.getJSONObject(0).getString(CLOSE_APPROACH_DATE)
//
//    val speed = approachData.getJSONObject(0).getJSONObject(RELATIVEVELOCITY).getString(KILOMETERS_PER_SECOND).toDouble()
//
//    val astronomical = approachData.getJSONObject(0).getJSONObject(MISS_DISTANCE).getString(ASTRONOMICAL).toDouble()
//
//    return CloseApproachData(closeApproachDate, RelativeVelocity(speed), MissDistance(astronomical))
//}
























