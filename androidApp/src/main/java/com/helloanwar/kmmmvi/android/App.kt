package com.helloanwar.kmmmvi.android

import android.app.Application
import com.helloanwar.kmmmvi.android.di.viewModelModule
import com.helloanwar.kmmmvi.di.initKoin
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            modules(
                viewModelModule
            )
        }
        Napier.base(DebugAntilog())
    }
}