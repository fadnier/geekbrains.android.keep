package org.sochidrive.keep.ui.note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_note.*
import org.sochidrive.keep.R
import org.sochidrive.keep.data.entity.Note
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity: AppCompatActivity() {
    companion object {
        private val EXTRA_NOTE = "note"
        private const val DATE_TIME_FORMAT = "dd.MM.yy HH:mm"

        fun start(context: Context, note: Note? = null) = Intent(context, NoteActivity::class.java).apply {
            putExtra(EXTRA_NOTE, note)
            context.startActivity(this)
        }
    }

    private var note: Note? = null
    lateinit var viewModel: NoteViewModel

    val textChangeListener = object: TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(p0: Editable?) = saveNote()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        note = intent.getParcelableExtra(EXTRA_NOTE)
        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        supportActionBar?.title = note?.lastChange?.let {
            SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(it)
        } ?: getString(R.string.new_keep)

        initView()
    }

    private fun initView() {
        note?.let {
            et_title.setText(it.title)
            et_body.setText(it.text)
            toolbar.setBackgroundColor(ResourcesCompat.getColor(resources,it.getColor(it.color),null))
        }
        et_title.addTextChangedListener(textChangeListener)
        et_body.addTextChangedListener(textChangeListener)
    }

    private fun saveNote() {
        if(et_title.text == null || et_title.text!!.length < 3) return

        note = note?.copy(
            title = et_title.text.toString(),
            text = et_body.text.toString(),
            lastChange = Date()
        ) ?: Note(UUID.randomUUID().toString(), et_title.text.toString(), et_body.text.toString())

        note?.let { viewModel.save(it) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}