package com.example.asteroidradar.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class viewModel : ViewModel(){

    //holds the json response as a string
    private val _stringDataOfAPi = MutableLiveData<String>()
    val stringData: LiveData<String>get() = _stringDataOfAPi

    //variable used to parse the string value into an object
    private val _neoNasaObject = MutableLiveData<NearEarthObjects>()
    val neoNasaObject:LiveData<NearEarthObjects>get()= _neoNasaObject
    //Fixed date range to experiment using api calls
    val startDate = "2019-09-08"
    val endDate = "2019-09-09"


    //request the image of the day from Nasa web service
    fun getImageFromApi(){
        var result = ""
        NasaApi.retrofitService.getImageOfTheDay().enqueue(object: Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    result = response.body().toString()
                    _stringDataOfAPi.value = result
                    Log.i("Retro", "onResponse ${response.body().toString()}")
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.i("Retro", "onFail ${t.message}")
                }

            })
        }



    fun gsonExample(){
        val gson = GsonBuilder().create()
        val nearEarthObjects = gson.fromJson(_stringDataOfAPi.value, NearEarthObjects::class.java)
        Log.i("RetrofitExample", " example ${nearEarthObjects.id}")
    }



    //request the nested Json file from the Nasa web service
    fun getAsteroidsFromApi(){
        var result = ""
        NasaApi.retrofitService.getAsteroids(startDate,endDate).enqueue(object: Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                result = response.body().toString()
                _stringDataOfAPi.value = result
                gsonExample()
                Log.i("Retro", "onResponse ${response.body().toString()}")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i("Retro", "onFail ${t.message}")
            }

        })
    }



    init {
       _stringDataOfAPi.value= ""
    }
}