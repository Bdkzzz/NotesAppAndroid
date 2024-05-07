package com.codercampy.notesapp.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Note(
    @PrimaryKey
    val id: String,
    val title: String,
    val body: String,
) : Parcelable