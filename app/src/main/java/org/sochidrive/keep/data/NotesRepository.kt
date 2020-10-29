package org.sochidrive.keep.data

import org.sochidrive.keep.data.entity.Note
import org.sochidrive.keep.data.provider.DataProvider
import org.sochidrive.keep.data.provider.FirestoreDataProvider

class NotesRepository(val dataProvider: DataProvider) {
    fun saveNote(note: Note) = dataProvider.saveNote(note)
    fun getNoteById(id: String) = dataProvider.getNoteById(id)
    fun getNotes() = dataProvider.getNotes()
    fun deleteNote(id: String) = dataProvider.deleteNote(id)
    fun getCurrentUser() = dataProvider.getCurrentUser()
}