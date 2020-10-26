package org.sochidrive.keep.ui.note

import org.sochidrive.keep.data.NotesRepository
import org.sochidrive.keep.data.entity.Note
import org.sochidrive.keep.data.model.NoteResult
import org.sochidrive.keep.ui.base.BaseViewModel

class NoteViewModel(): BaseViewModel<Note?, NoteViewState>() {

    init {
        viewStateLiveData.value = NoteViewState()
    }

    private var pendingNote: Note? = null

    fun save(note: Note) {
        pendingNote = note
    }

    override fun onCleared() {
        pendingNote?.let { NotesRepository.saveNote(it) }
    }

    fun loadNote(id: String) {
        NotesRepository.getNoteById(id).observeForever { result ->
            result?: return@observeForever
            when(result) {
                is NoteResult.Success<*> -> viewStateLiveData.value = NoteViewState(result.data as? Note)
                is NoteResult.Error -> viewStateLiveData.value = NoteViewState(error = result.error)
            }
        }
    }
}