package com.jason.stackoverflowusers.models

/**
 * Created by Jason on 3/3/18.
 */
data class UsersResponse(val items: ArrayList<User>? = ArrayList())

data class User(val display_name: String? = "",
                val profile_image: String? = "",
                var reputation: String? = "",
                val badge_counts: Badge? = Badge(0, 0, 0))

data class Badge(val gold: Int? = 0,
                 val silver: Int? = 0,
                 val bronze: Int? = 0)