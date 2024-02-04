package ru.plumsoftware.marvel.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import ru.plumsoftware.marvel.di.dataModule

class App : Application(), KoinComponent {

    companion object {
        lateinit var INSTANCE: App
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        startKoin {
            androidContext(this@App)
            modules(listOf(dataModule))
        }
    }
}