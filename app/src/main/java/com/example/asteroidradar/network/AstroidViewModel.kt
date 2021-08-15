package com.example.asteroidradar.network

import android.app.Application
import androidx.lifecycle.*
import com.example.asteroidradar.database.getDatabase
import com.example.asteroidradar.repository.Repository
import kotlinx.coroutines.launch


class AstroidViewModel( application : Application) : AndroidViewModel(application) {
      val database = getDatabase(application)
      val repository = Repository(database)

    //variable used to parse the string value into an object
    private val _neoNasaObject = MutableLiveData<MutableList<AsteroidDTO>>()
    val neoNasaObject: LiveData<MutableList<AsteroidDTO>> get() = _neoNasaObject

    //Fixed date range to experiment using api calls

    //TODO add a filter to the apiCall parameter
    fun apiCall() {
        //Add this to a repository class

        viewModelScope.launch {
           repository.refreshAsteroidsFromNetwork()
      }

    }

    init {
        apiCall()
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