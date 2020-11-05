package org.sochidrive.keep.ui.note

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import org.sochidrive.keep.data.NotesRepository
import org.sochidrive.keep.data.entity.Note
import org.sochidrive.keep.data.model.NoteResult
import org.sochidrive.keep.ui.base.BaseViewModel

class NoteViewModel(val notesRepository: NotesRepository) : BaseViewModel<NoteViewState.Data, NoteViewState>() {

    init {
        viewStateLiveData.value = NoteViewState()
    }

    private var pendingNote: Note? = null
    private var noteLiveData: LiveData<NoteResult>? = null
    private var deleteLiveData: LiveData<NoteResult>? = null
    private val noteObserver = object : Observer<NoteResult> {
        override fun onChanged(result: NoteResult?) {
            when (result) {
                is NoteResult.Success<*> -> {
                    pendingNote = result.data as? Note
                    viewStateLiveData.value = NoteViewState(NoteViewState.Data(pendingNote))
                }
                is NoteResult.Error -> viewStateLiveData.value = NoteViewState(error = result.error)
            }
            noteLiveData?.removeObserver(this)
        }
    }

    private val deleteObserver = object : Observer<NoteResult> {
        override fun onChanged(result: NoteResult?) {
            when (result) {
                is NoteResult.Success<*> ->{
                    pendingNote = null
                    viewStateLiveData.value = NoteViewState(NoteViewState.Data(isDeleted = true))
                }
                is NoteResult.Error -> viewStateLiveData.value = NoteViewState(error = result.error)
            }
            deleteLiveData?.removeObserver(this)
        }
    }

    fun save(note: Note) {
        pendingNote = note
    }

    public override fun onCleared() {
        pendingNote?.let {
            notesRepository.saveNote(it)
        }
        noteLiveData?.removeObserver(noteObserver)
    }

    fun loadNote(id: String) {
        noteLiveData = notesRepository.getNoteById(id)
        noteLiveData?.observeForever(noteObserver)
    }

    fun deleteNote() {
        pendingNote?.let {
            deleteLiveData = notesRepository.deleteNote(it.id)
            deleteLiveData?.observeForever(deleteObserver)
        }
    }
}