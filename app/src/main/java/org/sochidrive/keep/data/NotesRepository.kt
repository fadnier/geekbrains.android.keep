package org.sochidrive.keep.data

import org.sochidrive.keep.data.entity.Note
import org.sochidrive.keep.data.provider.DataProvider
import org.sochidrive.keep.data.provider.FirestoreDataProvider

object NotesRepository {
   val dataProvider: DataProvider = FirestoreDataProvider()

    fun saveNote(note: Note) = dataProvider.saveNote(note)
    fun getNoteById(id: String) = dataProvider.getNoteById(id)
    fun getNotes() = dataProvider.getNotes()
    fun getCurrentUser() = dataProvider.getCurrentUser()
}