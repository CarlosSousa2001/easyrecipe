package com.crs.receitafacil.ui

import android.app.Application
import com.crs.receitafacil.BuildConfig
import com.crs.receitafacil.core.utils.logging.DebugTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) {
            Timber.Forest.plant(DebugTree())
        }
    }
}