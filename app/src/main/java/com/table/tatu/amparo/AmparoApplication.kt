package com.table.tatu.amparo

import android.app.Application
import org.koin.core.context.startKoin
import com.table.tatu.amparo.di.appModule
import com.table.tatu.amparo.di.netWorkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class AmparoApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AmparoApplication)
            modules(appModule, netWorkModule)
        }
    }
}