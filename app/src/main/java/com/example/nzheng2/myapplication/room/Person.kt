package com.example.nzheng2.myapplication.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "person_table")
data class Person(@PrimaryKey @ColumnInfo(name = "first")val firstName:String, @ColumnInfo(name = "last")val lastName:String)