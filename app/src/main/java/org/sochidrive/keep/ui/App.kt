package org.sochidrive.keep.ui

import android.app.Application
import org.koin.android.ext.android.startKoin
import org.sochidrive.keep.di.appModule
import org.sochidrive.keep.di.mainModule
import org.sochidrive.keep.di.noteModule
import org.sochidrive.keep.di.splashModule

class App: Application() {

    companion object {
        var instance: App? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin(this, listOf(appModule, splashModule, mainModule, noteModule))
    }

}