package com.brighamandersen.internalize.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brighamandersen.internalize.data.PassageDao
import com.brighamandersen.internalize.entities.Passage
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID

class PassageViewModel(private val dao: PassageDao) : ViewModel() {

    val passages: StateFlow<List<Passage>> =
        dao.getAllPassages().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun getPassageById(id: String?): Passage? {
        if (id == null) return null
        return passages.value.find { it.id == id }
    }

    fun addPassage(newTitle: String, newBody: String) {
        val newPassage = Passage(
            id = UUID.randomUUID().toString(),
            title = newTitle,
            body = newBody
        )
        viewModelScope.launch {
            dao.insertPassage(newPassage)
        }
    }

    fun updatePassage(id: String, newTitle: String, newBody: String) {
        val passage = passages.value.find { it.id == id } ?: return
        val updated = passage.copy(title = newTitle, body = newBody)
        viewModelScope.launch {
            dao.updatePassage(updated)
        }
    }

    fun deletePassageById(id: String) {
      val passage = passages.value.find { it.id == id } ?: return
      viewModelScope.launch {
          dao.deletePassageById(passage.id)
      }
    }
}