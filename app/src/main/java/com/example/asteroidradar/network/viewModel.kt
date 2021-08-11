package com.example.asteroidradar.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.asteroidradar.*
import kotlinx.serialization.json.JsonArray
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.asteroidradar.CLOSE_APPROACH_DATE as CLOSE_APPROACH_DATE


class viewModel : ViewModel() {

    //variable used to parse the string value into an object
    private val _neoNasaObject = MutableLiveData<MutableList<Asteroid>>()
    val neoNasaObject: LiveData<MutableList<Asteroid>> get() = _neoNasaObject

    //Fixed date range to experiment using api calls
    val startDate = "2019-09-08"
    val endDate = "2019-09-09"
    val dates = mutableListOf<String>(startDate,endDate)

    //Function I am having trouble with I am trying to convert the json into an object
   private fun createObjectsFromJsonString(response: Response<String>) {

        //TODO add ViewModelScope
        val jsonObject = JSONTokener(response.body()).nextValue() as JSONObject
        val nearEarthJsonObject = jsonObject.getJSONObject("near_earth_objects")
        _neoNasaObject.value =  allocateJsonByDate(dates, nearEarthJsonObject)
    }


    //TODO add a filter to the apiCall parameter
    fun apiCall() {
        //TODO set a when  operator
        val response = getAsteroidsFromApi(startDate, endDate)
        response?.let { createObjectsFromJsonString(it) }
    }


    init {

    }
}