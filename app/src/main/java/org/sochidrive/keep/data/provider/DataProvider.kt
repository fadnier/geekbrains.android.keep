package org.sochidrive.keep.data.provider

import kotlinx.coroutines.channels.ReceiveChannel
import org.sochidrive.keep.data.entity.Note
import org.sochidrive.keep.data.entity.User
import org.sochidrive.keep.data.model.NoteResult

interface DataProvider {
    fun subscribeToNotes(): ReceiveChannel<NoteResult>
    suspend fun saveNote(note: Note): Note
    suspend fun getNoteById(id: String): Note
    suspend fun deleteNote(id: String)
    suspend fun getCurrentUser(): User?
}