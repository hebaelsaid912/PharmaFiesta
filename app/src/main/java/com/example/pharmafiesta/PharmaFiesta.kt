package com.example.pharmafiesta

import android.app.Application
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.pharmafiesta.utils.PharmaFiestaWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class PharmaFiesta: Application(){
    override fun onCreate() {
        handleWorkManager()
        super.onCreate()
    }
    private fun handleWorkManager(){
        // Create a Constraints object to define when the work should run
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED) // Example: Require network connectivity
            .build()

        // Create a PeriodicWorkRequest to specify the periodic work to be done
        val workRequest = PeriodicWorkRequestBuilder<PharmaFiestaWorker>(
            repeatInterval = 30, // Repeat interval in minutes
            repeatIntervalTimeUnit = TimeUnit.MINUTES,
        )/*.setInitialDelay(1,TimeUnit.HOURS)*/
            .setConstraints(constraints)
            .build()

        // Schedule the periodic work using WorkManager
        val workManager = WorkManager.getInstance(applicationContext)
        workManager.enqueue(workRequest)
    }
}