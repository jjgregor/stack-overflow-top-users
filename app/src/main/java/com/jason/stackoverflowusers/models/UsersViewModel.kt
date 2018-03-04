package com.jason.stackoverflowusers.models

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.jason.stackoverflowusers.WagApp
import com.jason.stackoverflowusers.services.StackOverflowService
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Jason on 3/3/18.
 */
class UsersViewModel(app:  Application) : AndroidViewModel(app) {

    @Inject lateinit var stackOverflowService: StackOverflowService

    var users: ArrayList<User> = ArrayList()

    init {
        (app as WagApp).getAppComponent().inject(this)
    }

    fun getUsers(): Observable<UsersResponse>? {
        return stackOverflowService.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }

}