package com.jason.stackoverflowusers.dagger

import com.jason.stackoverflowusers.WagApp
import com.jason.stackoverflowusers.activities.MainActivity
import com.jason.stackoverflowusers.models.UsersViewModel
import com.jason.stackoverflowusers.services.StackOverflowService
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Jason on 3/3/18.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class, StackOverflowServiceMod::class))
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(service: StackOverflowService)

    fun inject(wagApp: WagApp)

    fun inject(usersViewModel: UsersViewModel)

}