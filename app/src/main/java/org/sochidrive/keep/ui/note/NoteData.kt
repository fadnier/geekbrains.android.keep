package org.sochidrive.keep.ui.note

import org.sochidrive.keep.data.entity.Note

data class NoteData(val note: Note? = null, val isDeleted: Boolean = false)