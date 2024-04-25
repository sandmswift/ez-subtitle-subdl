package com.tosmart.launcher.ez_subtitle_subdl

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree

@HiltAndroidApp
class SubtitleApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // Plant timber
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}