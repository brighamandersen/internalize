package com.brighamandersen.internalize.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.brighamandersen.internalize.entities.Passage

@Database(entities = [Passage::class], version = 1, exportSchema = false)
abstract class PassageDatabase : RoomDatabase() {
    abstract fun passageDao(): PassageDao
} 