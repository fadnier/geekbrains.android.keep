package org.sochidrive.keep.data

import org.sochidrive.keep.data.entity.Note
import org.sochidrive.keep.data.provider.DataProvider

class NotesRepository(val dataProvider: DataProvider) {
    fun getNotes() = dataProvider.getNotes()
    fun saveNote(note: Note) = dataProvider.saveNote(note)
    fun getNoteById(id: String) = dataProvider.getNoteById(id)
    fun deleteNote(id: String) = dataProvider.deleteNote(id)
    fun getCurrentUser() = dataProvider.getCurrentUser()
}