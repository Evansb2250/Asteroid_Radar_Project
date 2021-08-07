package com.example.asteroidradar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//creates a database object
@Database(entities = arrayOf(Asteroid::class),version= 1, exportSchema = false)
abstract class AsteroidDatabase: RoomDatabase(){

    //saves instance of the database
     private lateinit var INSTANCE:AsteroidDatabase

     //abstract function that returns an AsteriodDao
     abstract fun asteroidDao():AsteroidDao

     //returns an instance of the database
     fun getDatabase(context: Context):AsteroidDatabase{
       if(INSTANCE == null){
           //builds a single instance of the database
           val instance = Room.databaseBuilder(context.applicationContext, AsteroidDatabase::class.java,
               "asteroid_database").build()
           //passes the reference to INSTANCE variable
           INSTANCE = instance
       }
         //returns instance of the database
         return INSTANCE
     }



}