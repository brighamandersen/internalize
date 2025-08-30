package com.brighamandersen.internalize.data

import androidx.room.*
import com.brighamandersen.internalize.entities.Passage
import kotlinx.coroutines.flow.Flow

@Dao
interface PassageDao {
    
    @Query("SELECT * FROM passages ORDER BY createdAt DESC")
    fun getAllPassages(): Flow<List<Passage>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPassage(passage: Passage)
    
    @Update
    suspend fun updatePassage(passage: Passage)
    
    @Query("DELETE FROM passages WHERE id = :id")
    suspend fun deletePassageById(id: String)
} 