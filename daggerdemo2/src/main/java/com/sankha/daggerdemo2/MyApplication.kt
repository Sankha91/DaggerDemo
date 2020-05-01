package com.sankha.daggerdemo2

import android.app.Application
import com.sankha.daggerdemo2.di.AppComponent
import com.sankha.daggerdemo2.di.DaggerAppComponent


class MyApplication : Application(){

    companion object{
        val TAG = "MyApplication"
    }
    lateinit private var appComponent : AppComponent
    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appContext(this)
            .build()
    }

    fun getAppComponent() : AppComponent {
        return appComponent
    }
}