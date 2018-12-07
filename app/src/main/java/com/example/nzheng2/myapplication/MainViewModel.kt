package com.example.nzheng2.myapplication

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.nzheng2.myapplication.room.Person
import com.example.nzheng2.myapplication.room.PersonDatabase
import com.example.nzheng2.myapplication.room.PersonRepositlory
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import kotlin.coroutines.experimental.CoroutineContext

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var users: MutableLiveData<Person>
    private var parentJob = Job()
    private val corountineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(corountineContext)
    private val repositlory: PersonRepositlory
    val allPerson: LiveData<List<Person>>

    init {
        val dao = PersonDatabase.getDatabase(application,scope).personDao()
        repositlory = PersonRepositlory(dao)
        allPerson = repositlory.allPerson
    }

    fun insert(person: Person) =
        scope.launch(Dispatchers.IO) {
            repositlory.insert(person)
        }

    fun removeAll()= scope.launch(Dispatchers.IO) {
        repositlory.removeAll()
    }
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

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}

