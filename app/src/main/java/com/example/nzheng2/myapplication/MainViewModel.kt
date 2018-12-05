package com.example.nzheng2.myapplication

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.nzheng2.myapplication.room.Person
import com.example.nzheng2.myapplication.room.PersonRepsitlory

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var users: MutableLiveData<Person>

//    private val repository: PersonRepsitlory
    fun getUsers(): LiveData<Person> {
        if (!::users.isInitialized) {
            users = MutableLiveData()
            loadUsers()
        }
        return users
    }

    private fun loadUsers() {
        users.value = Person("ccc", "ddd")
    }

}