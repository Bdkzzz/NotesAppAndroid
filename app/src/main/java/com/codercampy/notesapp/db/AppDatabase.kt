package com.codercampy.notesapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao

}

class DB(context: Context) {

    val db = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java, "codercampy.notes"
    ).allowMainThreadQueries().build()

}