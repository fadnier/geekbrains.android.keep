package org.sochidrive.keep.ui.main

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.activity_main.*
import org.sochidrive.keep.R
import org.sochidrive.keep.data.entity.Note
import org.sochidrive.keep.ui.base.BaseActivity
import org.sochidrive.keep.ui.note.NoteActivity
import org.sochidrive.keep.ui.splash.SplashActivity

class MainActivity: BaseActivity<List<Note>?, MainViewState>() {

    companion object {
        fun start(context: Context) = Intent(context, MainActivity::class.java).apply { context.startActivity(this) }
    }

    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    override val layoutRes = R.layout.activity_main
    lateinit var adapter: NotesRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rv_notes.layoutManager = GridLayoutManager(this,3)

        adapter = NotesRVAdapter {
            NoteActivity.start(this, it.id)
        }

        rv_notes.adapter = adapter

        fab.setOnClickListener {
            NoteActivity.start(this)
        }
    }

    override fun renderData(data: List<Note>?) {
        data?.let { adapter.notes = it }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean =
            MenuInflater(this).inflate(R.menu.main, menu).let { true }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when(item.itemId) {
                R.id.logout -> showLogoutDialog().let { true }
                else -> false
            }

    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
                .setTitle(R.string.logout_menu_title)
                .setMessage(R.string.you_understand)
                .setPositiveButton(R.string.Yes) {
                    dialog, which -> logout()
                }
                .setNegativeButton("Нет") { dialog, which -> dialog.dismiss()}
                .show()
    }

    fun logout() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener {
                    SplashActivity.start(this)
                    finish()
                }
    }

}