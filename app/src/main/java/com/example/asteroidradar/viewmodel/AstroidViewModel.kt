package com.example.asteroidradar.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.asteroidradar.DEBUG_LOG
import com.example.asteroidradar.database.getDatabase
import com.example.asteroidradar.domain.Asteroid
import com.example.asteroidradar.domain.ImageOfTheDay
import com.example.asteroidradar.repository.Repository
import kotlinx.coroutines.launch


class AstroidViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val repository = Repository(database)


    //variable used to parse the string value into an object
    val neoNasaObject: LiveData<List<Asteroid>> get() = repository.asteroids

    //image of the day Object
    val imageOfTheDayObject:LiveData<ImageOfTheDay?>get() = repository.imageOfTheDay


    //variable of Asteroid selected
    private val _asteroidSelected = MutableLiveData<Asteroid?>()
    val asteroidSelected : LiveData<Asteroid?>get()= _asteroidSelected


    fun setAsteroid(asteroid: Asteroid){
        _asteroidSelected.value = asteroid
    }

    fun unsetAsteroid(){
        _asteroidSelected.value = null
    }




    init {
        viewModelScope.launch {
            Log.i(DEBUG_LOG, "requesting data")
            viewModelScope.launch {
                repository.refreshAsteroidsFromNetwork()
                repository.refreshImageOfTheDay()

            }

        }
    }

}


class viewModelFactor(val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AstroidViewModel::class.java)) {
            return AstroidViewModel(application) as T
        } else
            throw IllegalArgumentException("Unable to construct viewmodel")

    }

}