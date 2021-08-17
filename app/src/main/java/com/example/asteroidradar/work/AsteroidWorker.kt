package com.example.asteroidradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.asteroidradar.database.getDatabase
import com.example.asteroidradar.repository.Repository
import retrofit2.HttpException

class AsteroidWorker( context: Context, workerParameters: WorkerParameters): CoroutineWorker(context,workerParameters) {

    companion object {
        val WORK_NAME = "Asteroid Worker"
    }


    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = Repository(database)
        return try {
            repository.refreshData()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}
