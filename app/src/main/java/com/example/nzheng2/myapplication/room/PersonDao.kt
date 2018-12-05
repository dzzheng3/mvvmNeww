package com.example.nzheng2.myapplication.room

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface PersonDao {
    @Query("SELECT * from person_table ORDER BY first ASC")
    fun getAllPersons(): LiveData<List<Person>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPerson(person: Person)

    @Query("DELETE from person_table")
    fun deleteAllPerson()
}