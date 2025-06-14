package com.brighamandersen.internalize.data

import android.content.Context
import com.brighamandersen.internalize.models.Passage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PassageRepository(private val context: Context) {
    private val gson = Gson()
    private val fileName = "passages.json"

    fun savePassagesToJson(passages: List<Passage>) {
        val json = gson.toJson(passages)
        context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
            it.write(json.toByteArray())
        }
    }

    fun loadPassagesFromJson(): List<Passage> {
        return try {
            val json = context.openFileInput(fileName).bufferedReader().use { it.readText() }
            val type = object : TypeToken<List<Passage>>() {}.type
            gson.fromJson(json, type) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }
} 