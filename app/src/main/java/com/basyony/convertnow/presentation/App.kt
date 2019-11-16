package com.basyony.convertnow.presentation

import androidx.multidex.MultiDexApplication
import com.basyony.convertnow.di.dashboardModule
import com.basyony.convertnow.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(listOf(networkModule, dashboardModule))
            androidContext(this@App)
        }
    }

}