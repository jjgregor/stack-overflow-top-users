package com.jason.stackoverflowusers.activities

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import com.jason.stackoverflowusers.R
import com.jason.stackoverflowusers.adapters.UsersAdapter
import com.jason.stackoverflowusers.models.UsersViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    private lateinit var viewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(UsersViewModel::class.java)

        viewModel.users?.let { bindUsers() } ?: getUsers()
    }

    private fun getUsers() {
        viewModel.getUsers()?.subscribe({ response ->
            response?.let {
                viewModel.users = it.items
                bindUsers()
            } ?: onError()
        }, { t: Throwable? ->
            onError()
            Log.e(TAG, "Error getting users.", t)
        })
    }

    private fun bindUsers() {
        viewModel.users?.let {
            progress_bar.visibility = View.GONE
            recycler_view.layoutManager = GridLayoutManager(this, 2)
            recycler_view.adapter = UsersAdapter(it)
            recycler_view.visibility = View.VISIBLE
        } ?: onError()
    }

    private fun onError() {
        progress_bar.visibility = View.GONE
        empty_view.visibility = View.VISIBLE
        Snackbar.make(constraint, R.string.get_users_error, Snackbar.LENGTH_LONG).show()
    }

}
