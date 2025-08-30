package com.brighamandersen.internalize.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "passages")
data class Passage(
    @PrimaryKey val id: String,
    val title: String,
    val body: String,
    val createdAt: Long = System.currentTimeMillis(),
)