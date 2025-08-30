package com.brighamandersen.internalize.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.brighamandersen.internalize.models.Passage
import java.util.UUID

class PassageViewModel : ViewModel() {
    private val _passages = mutableStateListOf<Passage>()
    val passages: List<Passage> get() = _passages

    fun getPassageById(id: String?): Passage? {
        if (id == null) {
            return null
        }
        return _passages.find { it.id == id }
    }

    fun addPassage(newTitle: String, newBody: String) {
        val newPassage = Passage(
            id = UUID.randomUUID().toString(),
            title = newTitle,
            body = newBody
        )
        _passages.add(newPassage)
    }

    fun deletePassage(id: String) {
        _passages.removeIf { it.id == id }
    }

    fun editPassage(id: String, newTitle: String, newBody: String) {
        val index = _passages.indexOfFirst { it.id == id }
        if (index != -1) {
            _passages[index] = _passages[index].copy(title = newTitle, body = newBody)
        }
    }
}