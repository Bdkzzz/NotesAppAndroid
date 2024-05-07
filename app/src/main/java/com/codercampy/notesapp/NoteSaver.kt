package com.codercampy.notesapp

import android.content.Context
import com.codercampy.notesapp.db.DB
import com.codercampy.notesapp.db.Note

class NoteSaver(context: Context) {

    private val db = DB(context)

    fun saveNote(note: Note) {
        db.db.noteDao().create(note)
    }

    fun getAllNotes(): List<Note> {
        return db.db.noteDao().getAll()
    }

    fun deleteNote(note: Note) {
        db.db.noteDao().delete(note)
    }

}