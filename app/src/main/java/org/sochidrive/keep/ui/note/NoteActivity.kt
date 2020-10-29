package org.sochidrive.keep.ui.note

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.activity_note.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.sochidrive.keep.R
import org.sochidrive.keep.data.entity.Note
import org.sochidrive.keep.ui.base.BaseActivity
import org.sochidrive.keep.common.getColorInt
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity: BaseActivity<NoteViewState.Data, NoteViewState>() {
    companion object {
        private val EXTRA_NOTE = "note"
        private const val DATE_TIME_FORMAT = "dd.MM.yy HH:mm"

        fun start(context: Context, note: String? = null) = Intent(context, NoteActivity::class.java).apply {
            putExtra(EXTRA_NOTE, note)
            context.startActivity(this)
        }
    }

    override val viewModel: NoteViewModel by viewModel()
    override val layoutRes = R.layout.activity_note
    private var note: Note? = null
    var color = Note.Color.WHITE


    val textChangeListener = object: TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(p0: Editable?) = saveNote()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val noteId = intent.getStringExtra(EXTRA_NOTE)

        noteId?.let {
            viewModel.loadNote(it)
        } ?: let {
            supportActionBar?.title = getString(R.string.new_keep)
        }

        initView()
    }

    override fun renderData(data: NoteViewState.Data) {
        if(data.isDeleted) finish()

        this.note = data.note
        supportActionBar?.title = note?.lastChange?.let {
            SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(it)
        }?: getString(R.string.new_keep)

        initView()
    }

    private fun initView() {
        et_title.removeTextChangedListener(textChangeListener)
        et_body.removeTextChangedListener(textChangeListener)

        note?.let {
            et_title.setText(it.title)
            et_body.setText(it.text)

            val color = when (it.color) {
                Note.Color.WHITE -> R.color.white
                Note.Color.YELLOW -> R.color.yellow
                Note.Color.GREEN -> R.color.green
                Note.Color.BLUE -> R.color.blue
                Note.Color.RED -> R.color.red
                Note.Color.VIOLET -> R.color.violet
            }
            toolbar.setBackgroundColor(ResourcesCompat.getColor(resources, color, null))
        }

        colorPicker.onColorClickListener = {
            toolbar.setBackgroundColor(it.getColorInt(this))
            color = it
            saveNote()
        }

        et_title.addTextChangedListener(textChangeListener)
        et_body.addTextChangedListener(textChangeListener)
    }

    private fun saveNote() {
        if(et_title.text == null || et_title.text!!.length < 3) return

        note = note?.copy(
            title = et_title.text.toString(),
            text = et_body.text.toString(),
            lastChange = Date(),
            color = color
        ) ?: Note(UUID.randomUUID().toString(), et_title.text.toString(), et_body.text.toString(), color = color)

        note?.let { viewModel.save(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu) = menuInflater.inflate(R.menu.note, menu).let { true }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        R.id.delete -> deleteNote().let { true }
        R.id.palette -> togglePicker().let { true }
        else -> super.onOptionsItemSelected(item)
    }

    fun deleteNote(){
        AlertDialog.Builder(this)
                .setTitle(R.string.note_delete_title)
                .setMessage(R.string.note_delete_message)
                .setPositiveButton(R.string.note_delete_ok) { dialog, which ->
                    viewModel.deleteNote()
                }
                .setNegativeButton(R.string.note_delete_cancel) { dialog, which -> dialog.dismiss() }
                .show()
    }

    fun togglePicker(){
        if(colorPicker.isOpen){
            colorPicker.close()
        } else {
            colorPicker.open()
        }
    }
}