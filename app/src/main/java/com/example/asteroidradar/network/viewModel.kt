package com.example.asteroidradar.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.asteroidradar.*
import org.json.JSONObject
import org.json.JSONTokener

import retrofit2.Response



class viewModel : ViewModel() {
    private val _apiCallBack = MutableLiveData<Response<String>>()
    val apiCalback : LiveData<Response<String>> get()= _apiCallBack

    //variable used to parse the string value into an object
    private val _neoNasaObject = MutableLiveData<MutableList<DataTransferObject>>()
    val neoNasaObject: LiveData<MutableList<DataTransferObject>> get() = _neoNasaObject

    //Fixed date range to experiment using api calls
    val startDate = "2019-09-08"
    val endDate = "2019-09-09"
    val dates = mutableListOf<String>(startDate,endDate)

    //Function I am having trouble with I am trying to convert the json into an object
   private fun createObjectsFromJsonString(response: Response<String>) {
        //TODO add ViewModelScope
        val jsonObject = JSONTokener(response.body()).nextValue() as JSONObject
        val nearEarthJsonObject = jsonObject.getJSONObject("near_earth_objects")
       // _neoNasaObject.value =  allocateJsonByDate(dates, nearEarthJsonObject)
        _neoNasaObject.value = jsonParser(dates, nearEarthJsonObject)
    }


    //TODO add a filter to the apiCall parameter
    fun apiCall() {
        //TODO set a when  operator
        getAsteroidsFromApi(startDate, endDate, _apiCallBack)
    }


    fun callBackReceived(callback: Response<String>){
        callback?.let {  createObjectsFromJsonString(callback!!)}
    }

}