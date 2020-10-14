package org.sochidrive.keep.ui.note

import androidx.lifecycle.ViewModel
import org.sochidrive.keep.data.NotesRepository
import org.sochidrive.keep.data.entity.Note

class NoteViewModel(): ViewModel() {
    private  var pendingNote: Note? = null

    fun save(note: Note) {
        pendingNote = note
    }

    override fun onCleared() {
        pendingNote?.let { NotesRepository.saveNote(it) }
    }
}