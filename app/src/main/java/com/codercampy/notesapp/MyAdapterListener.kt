package com.codercampy.notesapp

import com.codercampy.notesapp.db.Note

interface MyAdapterListener {

    fun onItemDelete(pos: Int, note: Note)

}