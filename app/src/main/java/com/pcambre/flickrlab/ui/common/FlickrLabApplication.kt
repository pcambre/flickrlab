package com.pcambre.flickrlab.ui.common

import android.app.Application
import com.pcambre.flickrlab.di.baseDIModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FlickrLabApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FlickrLabApplication)
            modules(baseDIModule)
        }
    }
}