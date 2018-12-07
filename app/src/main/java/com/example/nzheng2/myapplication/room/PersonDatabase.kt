package com.example.nzheng2.myapplication.room

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.launch

@Database(entities = [Person::class], version = 1)
public abstract class PersonDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: PersonDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): PersonDatabase {
            val temp = INSTANCE
            if (temp != null) {
                return temp
            }
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(context.applicationContext, PersonDatabase::class.java, "Person_database")
                        .addCallback(PersonDatabaseCallback(scope))
                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    abstract fun personDao(): PersonDao
    private class PersonDatabaseCallback(val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let {
                scope.launch(Dispatchers.IO) {
                    populateDatabase(it.personDao())
                }
            }

        }

        private fun populateDatabase(personDao: PersonDao) {
            personDao.deleteAllPerson()
            personDao.addPerson(Person("ab", "cd"))
            personDao.addPerson(Person("ab", "cc"))
        }
    }
}