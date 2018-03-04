package com.jason.stackoverflowusers.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.jason.stackoverflowusers.R
import com.jason.stackoverflowusers.databinding.UserCardViewBinding
import com.jason.stackoverflowusers.models.User

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
            user.reputation = String.format("%s Reputation", user.reputation)
            binding.user = user
            user.profile_image?.let {
                Glide.with(itemView.context)
                        .load(it)
                        .apply(RequestOptions()
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .placeholder(R.drawable.jon_snow))
//                        .listener(object :RequestListener<Drawable> {
//                            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
//                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                            }
//
//                            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
//                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                            }
//
//                        })
                        .into(binding.userImage)
            }
        }
    }
}