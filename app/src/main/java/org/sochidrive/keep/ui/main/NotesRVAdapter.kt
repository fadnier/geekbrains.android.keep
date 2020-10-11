package org.sochidrive.keep.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note.view.*
import org.sochidrive.keep.R
import org.sochidrive.keep.data.entity.Note

class NotesRVAdapter: RecyclerView.Adapter<NotesRVAdapter.ViewHolder>() {
    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(notes[position])

    override fun getItemCount(): Int = notes.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) = with(itemView) {
            tv_title.text = note.title
            tv_text.text = note.text
            setBackgroundColor(note.color)
        }
    }
}