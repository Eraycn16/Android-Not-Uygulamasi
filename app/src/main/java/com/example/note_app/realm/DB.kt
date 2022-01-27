package com.example.note_app.realm

import android.content.Context
import android.widget.Toast
import com.example.note_app.models.NoteModel
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.isLoaded
import io.realm.kotlin.where

class DB(val context: Context) {

    fun config() : Realm?{
        Realm.init(context)
        val conf = RealmConfiguration.Builder()
            .name("notes.db")
            .schemaVersion(1)
            .allowQueriesOnUiThread(true)
            .allowWritesOnUiThread(true)
            .build()
        return Realm.getInstance(conf)
    }

    fun noteAdd(baslik: String, icerik: String){
        val note = NoteModel()
        note.baslik = baslik
        note.icerik = icerik
        config()?.executeTransaction {
            it.insert(note)
        }
    }

    fun allNote():ArrayList<NoteModel>{
        val notList = ArrayList<NoteModel>()
        config()?.executeTransaction {
            it.where<NoteModel>().findAll().forEach{
                notList.add(it)
            }
        }
        return notList
    }

    fun deleteNote(id: String){
        config()?.executeTransaction {
            val remove = it.where<NoteModel>().equalTo("noteId",id).findFirst()
            remove?.deleteFromRealm()
        }
    }

    fun updateNote(id:String,baslik: String,icerik: String){
        config()?.executeTransaction {
            val updated = it.where<NoteModel>().equalTo("noteId",id).findFirst()
            updated?.baslik = baslik
            updated?.icerik = icerik
            it.insertOrUpdate(updated)
        }
    }
}