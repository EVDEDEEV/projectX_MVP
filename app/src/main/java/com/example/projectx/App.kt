package com.example.projectx

import android.app.Application
import com.example.projectx.di.appModule
import com.example.projectx.di.callModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                appModule,
                callModule
            )
        }
    }

}