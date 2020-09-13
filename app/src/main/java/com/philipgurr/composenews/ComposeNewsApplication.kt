package com.philipgurr.composenews

import android.app.Application
import com.philipgurr.composenews.di.AppContainer
import com.philipgurr.composenews.di.AppContainerImpl

class ComposeNewsApplication : Application() {
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainerImpl(this)
    }
}