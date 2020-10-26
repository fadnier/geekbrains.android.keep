package org.sochidrive.keep.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note.view.*
import org.sochidrive.keep.R
import org.sochidrive.keep.data.entity.Note
import org.sochidrive.keep.ui.base.getColor

class NotesRVAdapter(val onClickListener: ((Note) -> Unit)? = null): RecyclerView.Adapter<NotesRVAdapter.ViewHolder>() {
    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(notes[position])

    override fun getItemCount(): Int = notes.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) = with(itemView) {
            tv_title.text = note.title
            tv_text.text = note.text
            (itemView as CardView).setBackgroundColor(ResourcesCompat.getColor(resources, getColor(note.color), null))

            itemView.setOnClickListener {
                onClickListener?.invoke(note)
            }
        }
    }
}