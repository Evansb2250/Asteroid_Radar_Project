package com.example.asteroidradar

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
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
        var vm:AstroidViewModel
        var fact = viewModelFactor(this.application)
        vm = ViewModelProvider(this, fact).get(AstroidViewModel::class.java)

        vm.neoNasaObject.observe(this, Observer {
            Toast.makeText(this, "object received ${it.size}", Toast.LENGTH_LONG).show()
        })
    }


}