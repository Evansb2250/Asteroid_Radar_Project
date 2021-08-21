package com.example.asteroidradar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//creates a database object
@Database(entities = arrayOf(DatabaseAsteroid::class, DatabaseImageOfTheDay::class),version= 2, exportSchema = false)
abstract class AsteroidDatabase: RoomDatabase(){
     //abstract function that returns an AsteriodDao
     abstract fun asteroidDao():AsteroidDao
     abstract fun imageDao() : ImageOfTheDayDao

}



//saves instance of the database
private lateinit var INSTANCE:AsteroidDatabase

//returns an instance of the database
fun getDatabase(context: Context):AsteroidDatabase{
    synchronized(AsteroidDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            //builds a single instance of the database
            val instance = Room.databaseBuilder(
                context.applicationContext, AsteroidDatabase::class.java,
                "asteroid_database"
            ).build()
            //passes the reference to INSTANCE variable
            INSTANCE = instance
        }
    }
    //returns instance of the database
    return INSTANCE
}
