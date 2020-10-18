package org.sochidrive.keep.ui.note

import org.sochidrive.keep.data.entity.Note
import org.sochidrive.keep.ui.base.BaseViewState

class NoteViewState(note: Note? = null, error: Throwable? = null): BaseViewState<Note?> (note, error)