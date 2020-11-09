package org.sochidrive.keep.ui.main

import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import org.sochidrive.keep.data.NotesRepository
import org.sochidrive.keep.data.entity.Note
import org.sochidrive.keep.data.model.NoteResult
import org.sochidrive.keep.ui.base.BaseViewModel

class MainViewModel(val notesRepository: NotesRepository) : BaseViewModel<List<Note>?>() {

    private val repositoryNotes = notesRepository.getNotes()

    init {
        launch {
            repositoryNotes.consumeEach { result ->
                when (result) {
                    is NoteResult.Success<*> -> setData(result.data as? List<Note>)
                    is NoteResult.Error -> setError(result.error)
                }
            }
        }
    }

    @VisibleForTesting
    public override fun onCleared() {
        super.onCleared()
        repositoryNotes.cancel()
    }

}