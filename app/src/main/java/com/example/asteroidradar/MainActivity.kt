package com.example.asteroidradar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.asteroidradar.network.AstroidViewModel
import com.example.asteroidradar.network.viewModelFactor

class MainActivity : AppCompatActivity() {
    private val viewModel: AstroidViewModel by lazy {
        val activity = requireNotNull(this) {
            "You can only access the viewModel after onViewCreated()"
        }
        //The ViewModelProviders (plural) is deprecated.
        //ViewModelProviders.of(this, DevByteViewModel.Factory(activity.application)).get(DevByteViewModel::class.java)
        ViewModelProvider(this, viewModelFactor(activity.application)).get(AstroidViewModel::class.java)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)


        viewModel.apiCall()
    }


}