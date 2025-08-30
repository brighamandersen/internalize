package com.brighamandersen.internalize.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.brighamandersen.internalize.entities.Passage

@Database(entities = [Passage::class], version = 1, exportSchema = false)
abstract class PassageDatabase : RoomDatabase() {
    abstract fun passageDao(): PassageDao
    
    companion object {
        @Volatile
        private var INSTANCE: PassageDatabase? = null
        
        fun getDatabase(context: Context): PassageDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PassageDatabase::class.java,
                    "internalize_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
} 