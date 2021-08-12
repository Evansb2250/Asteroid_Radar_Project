package com.example.asteroidradar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.asteroidradar.network.viewModel

class MainActivity : AppCompatActivity() {
    val startDate = "2019-09-08"
    val endDate = "2019-09-09"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(viewModel::class.java)
        viewModel.apiCalback.observe(this, { response -> viewModel.callBackReceived(response)})
        viewModel.neoNasaObject.observe(this, { list -> Toast.makeText(this, "received list ", Toast.LENGTH_SHORT).show()})

        viewModel.apiCall()
    }
}