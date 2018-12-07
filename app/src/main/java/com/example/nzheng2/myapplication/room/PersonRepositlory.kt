package com.example.nzheng2.myapplication.room

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread

class PersonRepsitlory(private val personDao: PersonDao) {
    val allPerson: LiveData<List<Person>> = personDao.getAllPersons()
    @WorkerThread
    suspend fun insert(person: Person) {
        personDao.addPerson(person)
    }
}