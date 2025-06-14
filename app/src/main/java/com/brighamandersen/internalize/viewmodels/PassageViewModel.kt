package com.brighamandersen.internalize.viewmodels

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.brighamandersen.internalize.data.PassageRepository
import com.brighamandersen.internalize.models.Passage
import java.util.UUID

class PassageViewModel : ViewModel() {
    private val _passages = mutableStateListOf<Passage>()
    val passages: List<Passage> get() = _passages
    private var repository: PassageRepository? = null

    fun initialize(context: Context) {
        repository = PassageRepository(context)
        loadPassagesFromJson()
    }

    private fun loadPassagesFromJson() {
        repository?.let { repo ->
            _passages.clear()
            _passages.addAll(repo.loadPassagesFromJson())
        }
    }

    private fun savePassagesToJson() {
        repository?.let { repo ->
            repo.savePassagesToJson(_passages)
        }
    }

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
        savePassagesToJson()
    }

    fun deletePassage(id: String) {
        _passages.removeIf { it.id == id }
        savePassagesToJson()
    }

    fun editPassage(id: String, newTitle: String, newBody: String) {
        val index = _passages.indexOfFirst { it.id == id }
        if (index != -1) {
            _passages[index] = _passages[index].copy(title = newTitle, body = newBody)
            savePassagesToJson()
        }
    }
}