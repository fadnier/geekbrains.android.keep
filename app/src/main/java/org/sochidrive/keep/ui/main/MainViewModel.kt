package org.sochidrive.keep.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sochidrive.keep.data.NotesRepository

class MainViewModel: ViewModel() {
    private val viewStateLiveData: MutableLiveData<MainViewState> = MutableLiveData()

    init {
        NotesRepository.getNotes().observeForever {
            viewStateLiveData.value = viewStateLiveData.value?.copy(notes = it) ?: MainViewState(it)
        }
    }

    fun viewState(): LiveData<MainViewState> = viewStateLiveData
}