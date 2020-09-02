package com.logicsoulllp.classtest

import android.app.Application

/**
 * Application class
 */
class AppController : Application() {
    var instance: AppController? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}