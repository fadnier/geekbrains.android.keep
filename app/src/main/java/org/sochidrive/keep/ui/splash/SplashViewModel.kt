package org.sochidrive.keep.ui.splash

import org.sochidrive.keep.data.NotesRepository
import org.sochidrive.keep.data.errors.NoAuthException
import org.sochidrive.keep.ui.base.BaseViewModel

class SplashViewModel: BaseViewModel<Boolean?, SplashViewState>() {

    fun requestUser(){
        NotesRepository.getCurrentUser().observeForever{
            viewStateLiveData.value = it?.let { SplashViewState(true) } ?: let { SplashViewState(error = NoAuthException()) }
        }
    }
}