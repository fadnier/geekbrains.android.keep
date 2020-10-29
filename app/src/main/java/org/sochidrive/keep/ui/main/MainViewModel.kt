package org.sochidrive.keep.ui.main

import androidx.lifecycle.Observer
import org.sochidrive.keep.data.NotesRepository
import org.sochidrive.keep.data.entity.Note
import org.sochidrive.keep.data.model.NoteResult
import org.sochidrive.keep.ui.base.BaseViewModel

class MainViewModel(val notesRepository: NotesRepository): BaseViewModel<List<Note>?, MainViewState>() {

    private val notesObserver = Observer {result: NoteResult? ->
        result?: return@Observer
        when(result) {
            is NoteResult.Success<*> -> viewStateLiveData.value = MainViewState(result.data as? List<Note>)
            is NoteResult.Error -> viewStateLiveData.value = MainViewState(error =  result.error)
        }
    }

    private val repositoryNotes = notesRepository.getNotes()

    init {
        repositoryNotes.observeForever(notesObserver)
    }

    override fun onCleared() {
        super.onCleared()
        repositoryNotes.removeObserver(notesObserver)
    }
}