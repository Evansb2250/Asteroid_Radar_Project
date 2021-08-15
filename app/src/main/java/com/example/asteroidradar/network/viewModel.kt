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


class viewModel : ViewModel() {


    //variable used to parse the string value into an object
    private val _neoNasaObject = MutableLiveData<MutableList<AsteroidDTO>>()
    val neoNasaObject: LiveData<MutableList<AsteroidDTO>> get() = _neoNasaObject

    //Fixed date range to experiment using api calls

    //TODO add a filter to the apiCall parameter
    fun apiCall() {
        //Add this to a repository class
       viewModelScope.launch {
           val response = getAsteroidsFromApi()

           response?.let {
               val jsonObject = JSONTokener(response).nextValue() as JSONObject
               val nearEarthJsonObject = jsonObject.getJSONObject("near_earth_objects")
               _neoNasaObject.value = jsonParser(nearEarthJsonObject)
           }

       }
    }







}