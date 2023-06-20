package com.github.asadej0951.neversitupcurrency

import android.app.Application
import androidx.multidex.MultiDex
import com.github.asadej0951.neversitupcurrency.modules.networkModule
import com.github.asadej0951.neversitupcurrency.modules.repositoryModule
import com.github.asadej0951.neversitupcurrency.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class ProjectApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this@ProjectApplication)
        startKoin {
            androidContext(this@ProjectApplication)
            modules(arrayListOf(networkModule,repositoryModule,viewModelModule))
            androidLogger()
        }
    }
}