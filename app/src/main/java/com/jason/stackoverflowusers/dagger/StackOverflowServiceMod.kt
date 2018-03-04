package com.jason.stackoverflowusers.dagger

import com.jason.stackoverflowusers.services.StackOverflowService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by Jason on 3/3/18.
 */
@Module
class StackOverflowServiceMod {

    @Provides
    internal fun provideStackOverflowService(restAdapter: Retrofit) : StackOverflowService {
        return restAdapter.create(StackOverflowService::class.java)
    }

}