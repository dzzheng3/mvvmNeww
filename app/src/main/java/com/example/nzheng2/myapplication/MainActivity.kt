package com.example.nzheng2.myapplication

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.nzheng2.myapplication.databinding.ActivityMainBinding
import com.example.nzheng2.myapplication.room.Person
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        var activityMainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        activityMainBinding.person = Person("aaaaa", "bbbbb")
        viewModel.getUsers().observe(this, Observer {
            activityMainBinding.person = it
        })
    }

}
