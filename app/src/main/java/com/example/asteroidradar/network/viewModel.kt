package com.example.asteroidradar.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asteroidradar.*
import kotlinx.coroutines.launch
import org.json.JSONObject
import org.json.JSONTokener

import retrofit2.Response
import java.io.IOException


class viewModel : ViewModel() {


    //variable used to parse the string value into an object
    private val _neoNasaObject = MutableLiveData<MutableList<DataTransferObject>>()
    val neoNasaObject: LiveData<MutableList<DataTransferObject>> get() = _neoNasaObject

    //Fixed date range to experiment using api calls
    val startDate = "2019-09-08"
    val endDate = "2019-09-09"
    val dates = mutableListOf<String>(startDate,endDate)

    //TODO add a filter to the apiCall parameter
    fun apiCall() {
        //Add this to a repository class
       viewModelScope.launch {
           val response = getAsteroidsFromApi(startDate, endDate)
           response?.let {
               val jsonObject = JSONTokener(response).nextValue() as JSONObject
               val nearEarthJsonObject = jsonObject.getJSONObject("near_earth_objects")
               _neoNasaObject.value = jsonParser(dates, nearEarthJsonObject)
           }

       }
    }







}