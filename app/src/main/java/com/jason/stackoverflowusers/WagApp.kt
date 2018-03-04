package com.jason.stackoverflowusers

import android.app.Application
import com.facebook.stetho.Stetho
import com.jason.stackoverflowusers.dagger.AppComponent
import com.jason.stackoverflowusers.dagger.AppModule
import com.jason.stackoverflowusers.dagger.DaggerAppComponent
import com.jason.stackoverflowusers.dagger.NetworkModule

/**
 * Created by Jason on 3/3/18.
 */
class WagApp : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule(getString(R.string.base_url), applicationContext))
                .build()


        Stetho.initializeWithDefaults(this)
    }

    fun getAppComponent(): AppComponent = component

}