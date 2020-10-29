package org.sochidrive.keep.common

import org.sochidrive.keep.R
import org.sochidrive.keep.data.entity.Note

fun Note.getColor(color: Note.Color): Int {
    return when (color) {
        Note.Color.WHITE -> R.color.white
        Note.Color.YELLOW -> R.color.yellow
        Note.Color.GREEN -> R.color.green
        Note.Color.BLUE -> R.color.blue
        Note.Color.RED -> R.color.red
        Note.Color.VIOLET -> R.color.violet
    }
}