package com.pixelart.cvappchallenge

import android.app.Application
import com.pixelart.cvappchallenge.di.ApplicationComponent
import com.pixelart.cvappchallenge.di.DaggerApplicationComponent
import com.pixelart.cvappchallenge.di.NetworkModule

class AppController: Application() {
    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .networkModule(NetworkModule())
            .build()
    }
}