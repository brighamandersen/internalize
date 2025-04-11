package com.brighamandersen.internalize.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.brighamandersen.internalize.models.Passage  // Assuming this is your Passage data class
import java.util.UUID

class PassageViewModel : ViewModel() {
    private val _passages = mutableStateListOf<Passage>()
    val passages: List<Passage> get() = _passages

    init {
        val initialPassages = listOf(
            Passage("1", "Preamble", "We the people of the United States"),
            Passage("2", "Succeed Breathe", "When you wish to succeed as bad as you want to breathe, then you'll be successful."),
            Passage("3", "Shots Missed", "You miss 100% of the shots you don't take."),
        )
        _passages.addAll(initialPassages)
    }

    fun getPassageById(id: String?): Passage? {
        if (id == null) {
            return null
        }
        return _passages.find { it.id == id }
    }

    fun addPassage(title: String, body: String) {
        val newPassage = Passage(
            id = UUID.randomUUID().toString(), // Generate a unique ID (you might have your own ID logic)
            title = title,
            body = body
        )
        _passages.add(newPassage)
    }

    fun deletePassage(id: String) {
        _passages.removeIf { it.id == id }
    }
}