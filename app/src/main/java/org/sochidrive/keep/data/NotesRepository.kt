package org.sochidrive.keep.data

import org.sochidrive.keep.data.entity.Note
import org.sochidrive.keep.data.provider.DataProvider

class NotesRepository(val dataProvider: DataProvider) {
    fun getNotes() = dataProvider.subscribeToNotes()
    suspend fun saveNote(note: Note) = dataProvider.saveNote(note)
    suspend fun getNoteById(id: String) = dataProvider.getNoteById(id)
    suspend fun deleteNote(id: String) = dataProvider.deleteNote(id)
    suspend fun getCurrentUser() = dataProvider.getCurrentUser()
}