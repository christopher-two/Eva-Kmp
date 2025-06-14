package org.christophertwo.eva

import android.app.Application
import org.christophertwo.eva.di.ViewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class Application : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(ViewModelModules)
        }
    }
}