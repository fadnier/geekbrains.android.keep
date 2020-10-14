package org.sochidrive.keep.data

import org.sochidrive.keep.data.entity.Note

object NotesRepository {
    val notes = listOf(
            Note("Заметка 1","Текс первой заметки",0xff003333.toInt()),
            Note("Заметка 2","Текс второй заметки",0xfff14444.toInt()),
            Note("Заметка 3","Текс третьей заметки",0xff425555.toInt()),
            Note("Заметка 4","Текс четвертой заметки",0x8ff36666.toInt()),
            Note("Заметка 5","Текс пятой заметки",0xff047777.toInt())
    )
}