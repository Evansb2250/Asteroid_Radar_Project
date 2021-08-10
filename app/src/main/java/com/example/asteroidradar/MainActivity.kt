package com.example.asteroidradar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.asteroidradar.network.viewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(viewModel::class.java)


        viewModel.stringData.observe(this, Observer {
            Log.i("Retro", "my string ${it}")
            findViewById<TextView>(R.id.textView).text = it
        })

        
        viewModel.neoNasaObject.observe(this, Observer { element -> Log.i("Retro", "object ${element.id}") })

        viewModel.getAsteroidsFromApi()
    }
}