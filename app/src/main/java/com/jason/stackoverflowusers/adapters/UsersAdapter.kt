package com.jason.stackoverflowusers.adapters

import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Build
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
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
class UsersAdapter(private val users: ArrayList<User>) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bindUsers(users[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.user_card_view, parent, false))

    fun updateData(data: ArrayList<User>) {
        users.clear()
        users.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: UserCardViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindUsers(user: User) {

            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
                transformBadgeDrawables(binding.goldBadges, R.color.gold_badge)
                transformBadgeDrawables(binding.silverBadges, R.color.silver_badge)
                transformBadgeDrawables(binding.bronzeBadges, R.color.bronze_badge)
            }

            user.reputation = formatRepNumber(user.reputation)
            user.profile_image?.let {
                Glide.with(itemView.context)
                        .load(it)
                        .apply(RequestOptions()
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .placeholder(R.drawable.jon_snow))
                        .into(binding.userImage)
            }
            binding.user = user
            binding.root.setOnClickListener { user.link?.let { setupCustomTab(it) } }
        }

        private fun setupCustomTab(link: String) {
            CustomTabsIntent.Builder()
                    .addDefaultShareMenuItem()
                    .setToolbarColor(ContextCompat.getColor(binding.root.context, R.color.colorPrimary))
                    .setStartAnimations(binding.root.context, R.anim.slide_in_right, R.anim.slide_out_left)
                    .setExitAnimations(binding.root.context, R.anim.slide_in_left, R.anim.slide_out_right)
                    .setShowTitle(true)
                    .build()
                    .launchUrl(binding.root.context, Uri.parse(link))
        }

        private fun transformBadgeDrawables(badge: TextView, color: Int) {
            val wrappedDrawable = DrawableCompat.wrap(ContextCompat.getDrawable(badge.context, R.drawable.badge_dot))
            DrawableCompat.setTint(wrappedDrawable, ContextCompat.getColor(badge.context, color))
            badge.setCompoundDrawablesWithIntrinsicBounds(wrappedDrawable, null, null, null)
        }

        private fun formatRepNumber(reputation: String?): String {
            return if (reputation?.isNotEmpty() == true && !reputation.contains(",")) {
                val repInt = Integer.parseInt(reputation)
                String.format("%s Reputation", NumberFormat.getInstance().format(repInt))
            } else {
                reputation ?: ""
            }
        }
    }
}