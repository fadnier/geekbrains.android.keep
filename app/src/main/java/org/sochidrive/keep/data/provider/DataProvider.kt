package org.sochidrive.keep.data.provider

import androidx.lifecycle.LiveData
import org.sochidrive.keep.data.entity.Note
import org.sochidrive.keep.data.model.NoteResult
import org.sochidrive.keep.data.entity.User

interface DataProvider {
    fun getNotes(): LiveData<NoteResult>
    fun saveNote(note: Note): LiveData<NoteResult>
    fun getNoteById(id: String): LiveData<NoteResult>
    fun getCurrentUser(): LiveData<User?>
    fun deleteNote(id: String): LiveData<NoteResult>
}