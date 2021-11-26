package com.zvonimirplivelic.taskly

import android.app.Application
import timber.log.Timber

class TasklyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}