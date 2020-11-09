package org.sochidrive.keep.ui.splash

import kotlinx.coroutines.launch
import org.sochidrive.keep.data.NotesRepository
import org.sochidrive.keep.data.errors.NoAuthException
import org.sochidrive.keep.ui.base.BaseViewModel

class SplashViewModel(val notesRepository: NotesRepository) : BaseViewModel<Boolean>() {

    fun requestUser() = launch {
        notesRepository.getCurrentUser()?.let {
            setData(true)
        } ?: setError(NoAuthException())
    }
}
