package com.example.nzheng2.myapplication.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Person::class], version = 1)
public abstract class PersonDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: PersonDatabase? = null

        fun getDatabase(context: Context): PersonDatabase {
            val temp = INSTANCE
            if (temp != null) {
                return temp
            }
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(context.applicationContext, PersonDatabase::class.java, "Person_database")
                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    abstract fun personDao(): PersonDao
}