package com.example.asteroidradar.network

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.asteroidradar.DEBUG_LOG
import com.example.asteroidradar.database.getDatabase
import com.example.asteroidradar.domain.Asteroid
import com.example.asteroidradar.repository.Repository
import kotlinx.coroutines.launch


class AstroidViewModel( application : Application) : AndroidViewModel(application) {
      val database = getDatabase(application)
      val repository = Repository(database)

    //variable used to parse the string value into an object
    val neoNasaObject: LiveData<List<Asteroid>> get() = repository.asteroids




    //Fixed date range to experiment using api calls

    init {

        viewModelScope.launch {
            Log.i(DEBUG_LOG, "requesting data")
            repository.refreshAsteroidsFromNetwork()
        }
    }

}



class viewModelFactor(val application: Application):ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AstroidViewModel::class.java)){
            return AstroidViewModel(application) as T
        }else
            throw IllegalArgumentException("Unable to construct viewmodel")

    }

}