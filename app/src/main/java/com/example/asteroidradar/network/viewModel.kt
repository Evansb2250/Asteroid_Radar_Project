package com.example.asteroidradar.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.asteroidradar.*
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback

import retrofit2.Response



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
    fun apiCall(response: Response<String>) {
        //TODO set a when  operator
        Log.i("APICALL", response.toString())
         response?.let {  createObjectsFromJsonString(response!!)}
    }



    //request the nested Json file from the Nasa web service
    fun getAsteroidsFromApi(startDate: String, endDate:String){
        var result: Response<String>? = null
        val lock = true

        NasaApi.retrofitService.getAsteroids(startDate, endDate).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                result = response
                apiCall(result!!)
            }
            override fun onFailure(call: Call<String>, t: Throwable) {} })
    }


    init {

    }
}