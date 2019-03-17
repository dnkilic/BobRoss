package com.example.bobross.application

import android.app.Application
import com.squareup.leakcanary.LeakCanary

class ExampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupLeakCanary()
    }

    private fun setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }
}