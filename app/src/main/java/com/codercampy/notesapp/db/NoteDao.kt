package com.codercampy.notesapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.codercampy.notesapp.db.Note

@Dao
interface NoteDao {

    @Insert
    fun create(student: Note)

    @Query("SELECT * FROM Note")
    fun getAll(): List<Note>

    @Query("SELECT * FROM Note WHERE id = :id")
    fun get(id: String): Note

    @Update
    fun update(student: Note)

    @Delete
    fun delete(student: Note)

}