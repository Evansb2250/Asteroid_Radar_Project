package com.example.asteroidradar.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class viewModel : ViewModel(){


    val _data = MutableLiveData<String>()
    val data: LiveData<String>get() = _data

    val startDate = "2019-09-08"
    val endDate = "2019-09-09"


    fun sample(){
        var result = ""
        ImageApi.retrofitService.getImageOfTheDay().enqueue(object: Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    result = response.body().toString()
                    _data.value = result
                    Log.i("Retro", "onResponse ${response.body().toString()}")
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.i("Retro", "onFail ${t.message}")
                }

            })
        }



    fun asteroidExample(){
        var result = ""
        ImageApi.retrofitService.getAsteroids(startDate,endDate).enqueue(object: Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                result = response.body().toString()
                _data.value = result
                Log.i("Retro", "onResponse ${response.body().toString()}")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i("Retro", "onFail ${t.message}")
            }

        })
    }



    init {
       _data.value= ""
    }
}