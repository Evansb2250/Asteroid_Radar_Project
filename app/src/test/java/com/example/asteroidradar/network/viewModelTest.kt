package com.example.asteroidradar.network

import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RunWith(AndroidJUnit4::class)
class viewModelTest : TestCase(){
    @Test
    fun testAPICALL(){

        var result = ""
        ImageApi.retrofitService.getImageOfTheDay().enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                result = response.body().toString()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {

            }

        })
        println(result)
    }
}