package org.sochidrive.keep.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_note.*
import org.sochidrive.keep.R
import org.sochidrive.keep.common.getColorInt
import org.sochidrive.keep.data.entity.Note

class NotesRVAdapter(val onClickListener: ((Note) -> Unit)? = null) : RecyclerView.Adapter<NotesRVAdapter.ViewHolder>() {

    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_note,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(notes[position])

    override fun getItemCount() = notes.size

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(note: Note)  {
            with(note) {
                tv_title.text = title
                tv_text.text = text

                (itemView as CardView).setCardBackgroundColor(note.color.getColorInt(containerView.context))

                itemView.setOnClickListener {
                    onClickListener?.invoke(note)
                }
            }
        }
    }

}