package com.jason.stackoverflowusers.services

import com.jason.stackoverflowusers.models.UsersResponse
import retrofit2.http.GET
import rx.Observable

/**
 * Created by Jason on 3/3/18.
 */
interface StackOverflowService {

    @GET("2.2/users?site=stackoverflow")
    fun getUsers(): Observable<UsersResponse>

}