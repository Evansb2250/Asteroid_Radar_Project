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
fun jsonParser(dates: List<String>, jsonObject: JSONObject): ArrayList<DataTransferObject> {
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
        }
    }
    return listOfAsteroids
}



























