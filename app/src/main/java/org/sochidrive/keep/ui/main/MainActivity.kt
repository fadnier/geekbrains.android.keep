package org.sochidrive.keep.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.sochidrive.keep.R

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var adapter: NotesRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        rv_notes.layoutManager = GridLayoutManager(this,3)
        adapter = NotesRVAdapter()
        rv_notes.adapter = adapter

        viewModel.viewState().observe(this, {value-> value?.let { adapter.notes = it.notes }})

    }
}