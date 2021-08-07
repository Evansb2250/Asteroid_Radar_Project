package com.example.asteroidradar

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.asteroidradar.database.Asteroid
import com.example.asteroidradar.database.AsteroidDao
import com.example.asteroidradar.database.AsteroidDatabase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SimpleEntityReadWriteTest {
    private lateinit var asteroidDao: AsteroidDao
    private lateinit var db: AsteroidDatabase

    @Before
    fun createDb() {
       val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AsteroidDatabase::class.java
        ).allowMainThreadQueries().build()

        asteroidDao = db.asteroidDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(IOException::class)
     fun writeUserAndReadInList() {


        // create the data
        val asteroid = TestUtil.createAsteroid(
            1, 2, 0.4,
            21.3, true,
            12.3, 12.3
        )

        runBlocking {
            //insert the data to the database
            asteroidDao.insertAsteroid(asteroid)
        }

        //result
        var result: Asteroid? = null

        runBlocking {
            result = asteroidDao.getAsteroid(1)
        }

        //assertThat asteroid was entered into database
            assertEquals(true, result?.hazardous)

    }
}
