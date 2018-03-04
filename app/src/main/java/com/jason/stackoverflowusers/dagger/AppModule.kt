package com.jason.stackoverflowusers.dagger

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Jason on 3/3/18.
 */

@Module
class AppModule(var application: Application) {

    @Provides
    @Singleton
    fun providesApplication(): Application = application

}
