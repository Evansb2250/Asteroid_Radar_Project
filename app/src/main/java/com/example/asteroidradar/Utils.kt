package com.example.asteroidradar

import android.util.Log
import com.example.asteroidradar.network.AsteroidDTO
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

/*
Takes a list of dates  and the beginning location of the JSON ELEMENT
Cycles through the dates in the Json and creates a list Asteroids based on the API call response
 */


 fun jsonParser(jstokenAsObject: JSONObject): ArrayList<AsteroidDTO> {

    val listOfAsteroids = ArrayList<AsteroidDTO>()
    val jsonObject = jstokenAsObject.getJSONObject("near_earth_objects")

    //Time complexity of BIG O of (N*K)
    val dates = returnWeekAsArray()
    for (date in dates) {
        val jsonArray = jsonObject.getJSONArray(date)
        for (i in 0 until jsonArray.length()) {
            val transferObject = AsteroidDTO(
                jsonArray.getJSONObject(i).getString(JSON_ELEMENT_ID).toLong(),
                jsonArray.getJSONObject(i).getString(ABSOLUTE_MAGNITUDE).toDouble(),
                jsonArray.getJSONObject(i).getJSONObject(ESTIMATED_DIAMETERS)
                    .getJSONObject(KILOMETERS).getString(ESTIMATED_DIAMETER_MAX).toDouble(),
                jsonArray.getJSONObject(i).getString(IS_POTENTIALLY_HAZARDOUS_ASTEROID).toBoolean(),
                jsonArray.getJSONObject(i).getJSONArray(CLOSE_APPROACH_DATA).getJSONObject(0)
                    .getString(CLOSE_APPROACH_DATE),
                jsonArray.getJSONObject(i).getJSONArray(CLOSE_APPROACH_DATA).getJSONObject(0)
                    .getJSONObject(RELATIVEVELOCITY).getString(KILOMETERS_PER_SECOND).toDouble(),
                jsonArray.getJSONObject(i).getJSONArray(CLOSE_APPROACH_DATA).getJSONObject(0)
                    .getJSONObject(MISS_DISTANCE).getString(ASTRONOMICAL).toDouble()
            )
            listOfAsteroids.add(transferObject)
            Log.i(DEBUG_LOG, transferObject.toString())

        }
    }
    Log.i(DEBUG_LOG, listOfAsteroids.size.toString())

    return listOfAsteroids
}


fun returnWeekAsArray(): java.util.ArrayList<String> {

    val calendar = Calendar.getInstance()
    return java.util.ArrayList<String>().apply {
        for (i in 1..7) {
            calendar.add(Calendar.DATE, -1)
            add(DATEFORMAT.format(calendar.timeInMillis))
        }
    }
}




