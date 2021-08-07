package com.example.asteroidradar.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase


abstract class AsteroidDatabase: RoomDatabase(){

    //saves instance of the database
     private lateinit var INSTANCE:AsteroidDatabase
     //
     abstract fun dao():AsteroidDao

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