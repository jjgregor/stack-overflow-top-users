package com.jason.stackoverflowusers.adapters

import android.databinding.DataBindingUtil
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.jason.stackoverflowusers.R
import com.jason.stackoverflowusers.databinding.UserCardViewBinding
import com.jason.stackoverflowusers.models.User
import java.text.NumberFormat



/**
 * Created by Jason on 3/3/18.
 */
class UsersAdapter(val users: ArrayList<User>) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
        = holder.bindUsers(users[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
        = ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.user_card_view, parent, false))

    inner class ViewHolder(val binding: UserCardViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindUsers(user: User) {
            val repInt = Integer.parseInt(user.reputation)
            user.reputation = String.format("%s Reputation", NumberFormat.getInstance().format(repInt))
            binding.user = user
            user.profile_image?.let {
                Glide.with(itemView.context)
                        .load(it)
                        .apply(RequestOptions()
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .placeholder(R.drawable.jon_snow))
                        .into(binding.userImage)
            }

            binding.root.setOnClickListener {
                val builder = CustomTabsIntent.Builder()
                builder.setStartAnimations(binding.root.context, R.anim.slide_in_right, R.anim.slide_out_left)
                builder.setExitAnimations(binding.root.context, R.anim.slide_in_left, R.anim.slide_out_right)
                builder.build().launchUrl(binding.root.context, Uri.parse(user.link))
            }
        }
    }
}