package com.example.note_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.note_app.adapters.ListAdapter
import com.example.note_app.databinding.ActivityListBinding
import com.example.note_app.models.NoteModel
import com.example.note_app.realm.DB
import io.realm.kotlin.where

class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DB(this)
        binding.listNot.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        var notList = db.allNote()

        val adapter = ListAdapter(notList)
        binding.listNot.adapter = adapter
    }
}