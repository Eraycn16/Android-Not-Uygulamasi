package com.example.note_app.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class NoteModel : RealmObject(){

    @PrimaryKey
    var noteId = UUID.randomUUID().toString()

    var baslik:String = ""
    var icerik:String=""
}