package com.example.asteroidradar

import android.app.Application
import android.os.Build
import androidx.work.*
import com.example.asteroidradar.work.AsteroidWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class AsteroidApplication : Application() {

    //CoroutineDispatcher -
    private val applicationScope = CoroutineScope(Dispatchers.Default)

    /**
     * onCreate is called before the first screen is shown to the user.
     *
     * Use it to setup any background tasks, running expensive setup operations in a background
     * thread to avoid delaying app start.
     */
    override fun onCreate() {
        super.onCreate()
        delayedInit()
    }

    private fun delayedInit() {
        applicationScope.launch {
            setupRecurringWork()
        }
    }


    /**
     * Setup WorkManager background job to 'fetch' new network data daily.
     */
    private fun setupRecurringWork(){
        // builds a work request using our worker and the constraints we entered.
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setRequiresDeviceIdle(true)
                }
            }.build()




        val repeatingRequest =  PeriodicWorkRequestBuilder<AsteroidWorker>(1, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        /*
         After you define your WorkRequest, you can schedule it with WorkManager, using the
         enqueueUniquePeriodicWork() method. This method allows you to add a uniquely named
         PeriodicWorkRequest to the queue, where only one PeriodicWorkRequest of a particular
         name can be active at a time.
         */

        /*
         schedule the work using the enqueueUniquePeriodicWork() method. Pass in the KEEP enum for
         the ExistingPeriodicWorkPolicy. Pass in repeatingRequest as the PeriodicWorkRequest parameter.
         */

        WorkManager.getInstance().enqueueUniquePeriodicWork(AsteroidWorker.WORK_NAME, ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )


        /*
        If pending (uncompleted) work exists with the same name, the ExistingPeriodicWorkPolicy.KEEP parameter makes the WorkManager
        keep the previous periodic work and discard the new work request.
         */

    }

}