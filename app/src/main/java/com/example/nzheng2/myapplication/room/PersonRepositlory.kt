package com.example.nzheng2.myapplication.room

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread

class PersonRepositlory(private val personDao: PersonDao) {
    val allPerson: LiveData<List<Person>> = personDao.getAllPersons()
    @WorkerThread
    suspend fun insert(person: Person) {
        personDao.addPerson(person)
    }

    @WorkerThread
    suspend fun removeAll() {
        personDao.deleteAllPerson()
    }
}