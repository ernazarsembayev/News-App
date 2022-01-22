package com.ernazar.newsapp

import android.app.Application
import androidx.databinding.ktx.BuildConfig
import com.ernazar.newsapp.utils.di.appModule
import com.ernazar.newsapp.utils.di.dataModule
import com.ernazar.newsapp.utils.di.domainModule
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
            modules(listOf(appModule, dataModule, domainModule))
        }
    }
}