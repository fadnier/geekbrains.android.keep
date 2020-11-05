package org.sochidrive.keep.ui.note

import org.sochidrive.keep.data.entity.Note
import org.sochidrive.keep.ui.base.BaseViewState

class NoteViewState(data: Data = Data(), error: Throwable? = null) : BaseViewState<NoteViewState.Data>(data, error) {
    class Data(val note: Note? = null, val isDeleted: Boolean = false)
}