package org.sochidrive.keep.ui.main

import org.sochidrive.keep.data.entity.Note
import org.sochidrive.keep.ui.base.BaseViewState

class MainViewState(val notes: List<Note>? = null, error: Throwable? = null) :BaseViewState<List<Note>?>(notes, error)