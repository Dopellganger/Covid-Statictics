package com.example.app

import android.app.Application
import com.example.di.dataModule
import com.example.di.domainModule
import com.example.di.presentationModule
import com.example.presentation.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            modules(listOf(presentationModule, domainModule, dataModule))
        }
    }
}