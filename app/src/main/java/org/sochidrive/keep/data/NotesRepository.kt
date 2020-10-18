package org.sochidrive.keep.data

import androidx.lifecycle.MutableLiveData
import org.sochidrive.keep.data.entity.Note
import java.util.*

object NotesRepository {
    private val notesLiveData = MutableLiveData<List<Note>>()

    private val notes = mutableListOf(
            Note(UUID.randomUUID().toString(),"Заметка 1","Текс первой заметки",Note.Color.WHITE),
            Note(UUID.randomUUID().toString(),"Заметка 2","Текс второй заметки",Note.Color.YELLOW),
            Note(UUID.randomUUID().toString(),"Заметка 3","Текс третьей заметки",Note.Color.GREEN),
            Note(UUID.randomUUID().toString(),"Заметка 4","Текс четвертой заметки",Note.Color.BLUE),
            Note(UUID.randomUUID().toString(),"Заметка 5","Текс пятой заметки",Note.Color.RED),
            Note(UUID.randomUUID().toString(),"Заметка 6","Текс шестой заметки",Note.Color.VIOLET)
    )

    init {
        notesLiveData.value = notes
    }

    fun saveNote(note: Note) {
        addOrReplace(note)
        notesLiveData.value = notes
    }

    fun addOrReplace(note: Note) {
        for (i in notes.indices) {
            if(notes[i] == note) {
                notes[i] = note
                return
            }
        }
        notes.add(note)
    }

    fun getNotes() = notesLiveData
}