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
class AsteroidDatabaseTest {
    private lateinit var asteroidDao: AsteroidDao
    private lateinit var db: AsteroidDatabase


    @Before
    fun createDb() {
        //Creates context for the test
        val context = ApplicationProvider.getApplicationContext<Context>()
        //Uses context and build database to store data in memory instead of database
        db = Room.inMemoryDatabaseBuilder(
            context, AsteroidDatabase::class.java
        ).build()

        // passes reference to dao
        asteroidDao = db.asteroidDao()
    }


    @After
    @Throws(IOException::class)
    fun closeDb() {
        // close db once all test are ran
        db.close()
    }


    //TODO add test add a list of asteroids to a database
    @Test
    @Throws(IOException::class)
    fun insertAsteroidTest() {
        // create the data
        val asteroid = TestUtil.createAsteroid(
            1, 2, 0.4,
            21.3, true,
            12.3, 12.3
        )

        // run database request inside coroutineScope
        //insert the data to the database
        runBlocking { asteroidDao.insertAsteroid(asteroid) }

        //result
        var result: Asteroid? = null

        // run database request inside coroutineScope
        runBlocking { result = asteroidDao.getAsteroid(1) }

        //assertThat asteroid was entered into database
        assertEquals(true, result?.hazardous)
    }


    //TODO add test to remove old meteors out the database after 7 days
    @Test
    @Throws(IOException::class)
    fun removeAsteroidTest() {

    }


    //TODO add test to return asteroids in descending order


}
