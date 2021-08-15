package com.example.asteroidradar.networkTest

import com.example.asteroidradar.database.DatabaseAsteroid
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.io.IOException
import kotlin.jvm.Throws

class NetworkTest_Suite {

    //TODO create reqs for retrofit


    //TODO test getting a set of asteroids
    @Test
    fun httpRequestAsteroids_testSize_greaterThanZero() {
        //request data
        val result: List<DatabaseAsteroid>? = null

        assertEquals(false, result?.isEmpty())
    }


    //TODO test getting a set of asteroids fails
    @Test
    fun httpRequestAsteroids_testSize_failedRequest() {
        //request data
        val result: List<DatabaseAsteroid>? = null

        assertEquals(false, result?.isEmpty())
    }


    //TODO  test getting an image success
    @Test
    fun httpRequestImageOfTheDay_notNull() {
        //request data
        val result: List<DatabaseAsteroid>? = null

        assertEquals(false, result?.isEmpty())
    }


    //TODO  test getting an image and fails
    @Test
    @Throws(IOException::class)
    fun httpRequestImageOfTheDay_failedRequest() {
        //request data
        val result: List<DatabaseAsteroid>? = null

        assertEquals(false, result?.isEmpty())
    }

}