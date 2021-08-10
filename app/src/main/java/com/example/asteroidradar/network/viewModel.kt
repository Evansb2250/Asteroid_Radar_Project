package com.example.asteroidradar.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

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
                    //turn response into a string
                    result = response.body().toString()
                    _stringDataOfAPi.value = result
                    Log.i("Retro", "onResponse ${response.body().toString()}")
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.i("Retro", "onFail ${t.message}")
                }

            })
        }


    //Function I am having trouble with I am trying to convert the json into an object


    fun createObjectsFromJsonString(){
        val format = Json { ignoreUnknownKeys = true}
        val data = _stringDataOfAPi.value?.let { format.decodeFromString<TransferDomainObjects>(it) }
        Log.i("RetrofitExample", " example ${data}")
    }



    //request the nested Json file from the Nasa web service
    fun getAsteroidsFromApi(){
        var result = ""
        NasaApi.retrofitService.getAsteroids(startDate,endDate).enqueue(object: Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                result = response.body().toString()
                _stringDataOfAPi.value = result


                createObjectsFromJsonString()
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